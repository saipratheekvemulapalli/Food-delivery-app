import React, { useEffect, useState } from 'react';
import photo28 from './photo28.jpg';
import './RestaurantList.css';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const RestaurantList = ({ isAdmin }) => {
  console.log('RestaurantList isAdmin:', isAdmin);
  const [restaurants, setRestaurants] = useState([]);
  const [filteredRestaurants, setFilteredRestaurants] = useState([]);
  const navigate = useNavigate()
  
  const [searchTerm, setSearchTerm] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedRestaurant, setSelectedRestaurant] = useState(null);
  const [foodItems, setFoodItems] = useState([]);

  // const [restaurantToDelete, setRestaurantToDelete] = useState(null);
  const [restaurantToDelete, setRestaurantToDelete] = useState(null); // For tracking the restaurant to delete
const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false); // For tracking the delete modal state


  useEffect(() => {
    // Fetching the restaurants from the backend
    axios
      .get(
        'http://localhost:8080/registration/authorization/viewAllRestaurants',
      )
      .then((response) => {
        console.log('Fetched restaurants:', response.data);
        setRestaurants(response.data);
        setFilteredRestaurants(response.data);
      })
      .catch((error) => {
        console.error('Error fetching restaurants:', error);
      });
  }, []);

  const handleSearchChange = (e) => {
    const term = e.target.value;
    setSearchTerm(term);

    // Filter restaurants based on the search term
    const filtered = restaurants.filter((restaurant) =>
      restaurant.restaurantName.toLowerCase().includes(term.toLowerCase()),
    );
    setFilteredRestaurants(filtered);
  };


  
  const handleDeleteSubmit = () => {
    
    if (!restaurantToDelete) {
      console.error("No restaurant selected for deletion.");
      return; 
    }
  
    const token = localStorage.getItem('token');
  
    axios
      .delete(`http://localhost:8080/registration/authorization/deleteRestaurant/${restaurantToDelete.restaurantId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log('Restaurant deleted successfully:', response.data);
        setIsDeleteModalOpen(false); // Close the modal after deletion
  
        // Remove the deleted restaurant from the list
        setRestaurants((prevRestaurants) =>
          prevRestaurants.filter((restaurant) => restaurant.restaurantId !== restaurantToDelete.restaurantId)
        );
        setFilteredRestaurants((prevRestaurants) =>
          prevRestaurants.filter((restaurant) => restaurant.restaurantId !== restaurantToDelete.restaurantId)
        );
  
        setRestaurantToDelete(null); // Clear the state after deletion
      })
      .catch((error) => {
        console.error('Error deleting restaurant:', error.response?.data || error.message);
        alert("Failed to delete restaurant: " + (error.response?.data.message || error.message));
      });
  };
  

  
  const handleDeleteClick = (restaurant) => {
    setRestaurantToDelete(restaurant);
    setIsDeleteModalOpen(true);
  };
  
  const handleDeleteModalClose = () => {
    setIsDeleteModalOpen(false);
    setRestaurantToDelete(null);
  };

  const handleUpdateClick = (restaurant) => {
    setSelectedRestaurant(restaurant);
    setIsModalOpen(true); 
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
    setSelectedRestaurant(null);
  };

  const handleUpdateSubmit = () => {
    const restaurantId = selectedRestaurant.restaurantId;
    
    // Get the token from localStorage
    const token = localStorage.getItem('token');
  
    console.log('Data being sent to backend:', selectedRestaurant); // Log the data
    
    axios
      .put(
        `http://localhost:8080/registration/authorization/updateRestaurant/${restaurantId}`,
        selectedRestaurant,
        {
          headers: {
            Authorization: `Bearer ${token}` // Include token in the headers
          }
        }
      )
      .then((response) => {
        console.log('Restaurant updated successfully:', response.data);
        alert("Restaurant updated successfully");
        setIsModalOpen(false);
        setRestaurants((prevRestaurants) =>
          prevRestaurants.map((restaurant) =>
            restaurant.restaurantId === restaurantId ? response.data : restaurant
          )
        );
        setFilteredRestaurants((prevRestaurants) =>
          prevRestaurants.map((restaurant) =>
            restaurant.restaurantId === restaurantId ? response.data : restaurant
          )
        );
      })
      .catch((error) => {
        console.error('Error updating restaurant:', error.response.data);
        alert("Failed to update restaurant: " + error.response.data.message);
      });
  };
  
  const handleSelectFoodClick = (restaurantName) => {
    navigate(`/restaurantfooditems?restaurantName=${restaurantName}`);
  
    // Fetch food items for the selected restaurant
    axios.get(`http://localhost:8080/registration/authorization/item/getItemsByrestaurantName/${restaurantName}`)
      .then((response) => {
        console.log('Fetched food items:', response.data);
        setFoodItems(response.data); // Save the fetched food items in state
      })
      .catch((error) => {
        console.error('Error fetching food items:', error);
      });
  };
  
  

  return (
    <div
      className="relative min-h-screen bg-cover bg-center p-8"
      style={{ backgroundImage: `url(${photo28})`, opacity: 1.2 }}
    >
      <div className="flex justify-between items-center mb-8">
        {isAdmin && (
          <Link to='/addrestaurant'>
          <button
            type="button"
            className="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700"
          >
            Add Restaurant
          </button>
          </Link>
        )}
        <form className="flex items-center max-w-sm">
          <label htmlFor="simple-search" className="sr-only">
            Search
          </label>
          <div className="relative w-full">
            <input
              type="text"
              id="simple-search"
              value={searchTerm}
              onChange={handleSearchChange}
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-64 pl-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Search Restaurant Name"
              required
            />
          </div>
        </form>
      </div>

      {/* Table */}
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr className='hover:bg-gray-50 dark:hover:bg-gray-900'>
              {isAdmin ? (
                <>
                  <th scope="col" className="px-6 py-3">
                    Restaurant Id
                  </th>
                  <th scope="col" className="px-6 py-3">
                    Restaurant Name
                  </th>
                  <th scope="col" className="px-6 py-3">
                    Food Type
                  </th>
                  <th scope="col" className="px-6 py-3">
                    Location
                  </th>
                  <th scope="col" className="px-6 py-3">
                    <span className="sr-only">Action</span>
                  </th>
                </>
              ) : (
                <>
                  <th scope="col" className="px-6 py-3">
                    Restaurant Name
                  </th>
                  <th scope="col" className="px-6 py-3">
                    Rating
                  </th>
                  <th scope="col" className="px-6 py-3">
                    Food Type
                  </th>
                  <th scope="col" className="px-6 py-3">
                    Location
                  </th>
                  <th scope="col" className="px-6 py-3">
                    <span className="sr-only">Action</span>
                  </th>
                </>
              )}
            </tr>
          </thead>
          <tbody>
            {filteredRestaurants.map((restaurant) => (
                restaurant.restaurantId ? (
              <tr
                key={restaurant.restaurantId}
                className="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-900"
              >
                {isAdmin ? (
                  <>
                    <td className="px-6 py-4">{restaurant.restaurantId}</td>
                    <td className="px-6 py-4">{restaurant.restaurantName}</td>
                    <td className="px-6 py-4">{restaurant.type}</td>
                    <td className="px-6 py-4">{restaurant.location}</td>
                    <td className="px-6 py-4 text-right">
                      <button
                        type="button"
                        className="px-3 py-2 text-xs text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                        onClick={() => handleUpdateClick(restaurant)}
                      >
                        Update Restaurant
                      </button>
                      <button
                        type="button"
                        onClick={() => handleDeleteClick(restaurant)}
                        className="px-3 py-2 text-xs text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
                      >
                        Delete
                      </button>
                      <button
                        type="button"
                        className="px-3 py-2 text-xs text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
                        onClick={() => handleSelectFoodClick(restaurant.restaurantName)}
                        
                      >
                        Select Food
                      </button>
                    </td>
                  </>
                ) : (
                  <>
                    <td className="px-6 py-4">{restaurant.restaurantName}</td>
                    <td className="px-6 py-4">{restaurant.rating}</td>
                    <td className="px-6 py-4">{restaurant.type}</td>
                    <td className="px-6 py-4">{restaurant.location}</td>
                    <td className="px-6 py-4 text-right">
                    <button
                        type="button"
                        className="px-3 py-2 text-xs text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
                        onClick={() => handleSelectFoodClick(restaurant.restaurantName)}
                      >
                        Select Food
                      </button>
                    </td>
                  </>
                )}
              </tr>
              ) : null
            ))}
          </tbody>
        </table>
      </div>
      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-75">
          <div className="bg-white p-6 rounded-lg w-1/2">
            <h2 className="text-xl font-bold mb-4">Update Restaurant</h2>
            <form>
              <div className="mb-4">
                <label className="block text-gray-700">Restaurant Id</label>
                <input
                  type="text"
                  value={selectedRestaurant.restaurantId}
                  onChange={(e) =>
                    setSelectedRestaurant({
                      ...selectedRestaurant,
                      restaurantId: e.target.value,
                    })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Restaurant Name</label>
                <input
                  type="text"
                  value={selectedRestaurant.restaurantName}
                  onChange={(e) =>
                    setSelectedRestaurant({
                      ...selectedRestaurant,
                      restaurantName: e.target.value,
                    })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Rating</label>
                <input
                  type="text"
                  value={selectedRestaurant.rating}
                  onChange={(e) =>
                    setSelectedRestaurant({
                      ...selectedRestaurant,
                      rating: e.target.value,
                    })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Food Type</label>
                <input
                  type="text"
                  value={selectedRestaurant.type} 
                  onChange={(e) =>
                    setSelectedRestaurant({
                      ...selectedRestaurant,
                      type: e.target.value,
                    })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Location</label>
                <input
                  type="text"
                  value={selectedRestaurant.location}
                  onChange={(e) =>
                    setSelectedRestaurant({
                      ...selectedRestaurant,
                      location: e.target.value,
                    })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
              </div>
              <button
                type="button"
                onClick={handleUpdateSubmit}
                className="bg-blue-500 text-white px-4 py-2 rounded-md mr-2"
              >
                Update
              </button>
              <button
                type="button"
                onClick={handleModalClose}
                className="bg-gray-500 text-white px-4 py-2 rounded-md"
              >
                Cancel
              </button>
            </form>
          </div>
        </div>
      )}


{isDeleteModalOpen && (
  <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-75">
    <div className="bg-white p-6 rounded-lg w-1/2">
      <h2 className="text-xl font-bold mb-4">Delete Restaurant</h2>
      <p className="mb-4">Are you sure you want to delete the restaurant <strong>{restaurantToDelete.restaurantName}</strong>?</p>
      <div className="flex justify-end">
        <button
          type="button"
          onClick={handleDeleteSubmit}
          className="bg-red-500 text-white px-4 py-2 rounded-md mr-2"
        >
          Delete
        </button>
        <button
          type="button"
          onClick={handleDeleteModalClose}
          className="bg-gray-500 text-white px-4 py-2 rounded-md"
        >
          Cancel
        </button>
      </div>
    </div>
  </div>
)}
    </div>
  );
};

export default RestaurantList;
