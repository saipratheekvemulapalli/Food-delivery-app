import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useLocation } from 'react-router-dom';
import photo74 from './photo74.jpg';
import axiosInstance from './axiosInstance'; // Adjust the path if necessary
import { useNavigate } from 'react-router-dom';

const RestaurantFoodItems = ({isAdmin}) => {
  const navigate = useNavigate();
  const location = useLocation();
  const restaurantName = new URLSearchParams(location.search).get(
    'restaurantName',
  );

  const [foodItems, setFoodItems] = useState([]);
  const [filteredFoodItems, setFilteredFoodItems] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedItem, setSelectedItem] = useState(null);
  const [isDetailModalOpen, setIsDetailModalOpen] = useState(false);
  const [updatedItem, setUpdatedItem] = useState({
    itemName: '',
    description: '',
    category: '',
    price: '',
    image: '',
  });
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [itemToDelete, setItemToDelete] = useState(null);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);

  useEffect(() => {
    const fetchFoodItems = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/registration/authorization/item/getItemsByrestaurantName/${restaurantName}`,
        );
        const data = await response.json();
        console.log(data); 

        setFoodItems(data);
        setFilteredFoodItems(data); 
      } catch (error) {
        console.error('Error fetching food items:', error);
      }
    };

    fetchFoodItems();
  }, [restaurantName]);

    const handleSearchChange = (e) => {
      const value = e.target.value.toLowerCase();
      setSearchTerm(value);

      const filtered = foodItems.filter(item =>
        item.itemName.toLowerCase().includes(value)
      );
      setFilteredFoodItems(filtered);
    };

  //   const handleSearchChange = (event) => {
  //     setSearchTerm(event.target.value);
  //   };

//   const handleSearchChange = (event) => {
//     setSearchTerm(event.target.value);
//   };

  const filteredItems = filteredFoodItems.filter(
    (item) =>
      item.itemName &&
      item.itemName.toLowerCase().includes(searchTerm.toLowerCase()),
  );
  const handleUpdateItem = (item) => {
    setSelectedItem(item);
    setUpdatedItem({
      itemName: item.itemName,
      description: item.description,
      category: item.category,
      price: item.price,
      image: item.image,
    });
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setSelectedItem(null);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUpdatedItem((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleUpdateSubmit = () => {
    if (!selectedItem) return;

    const updatedData = {
      itemId: selectedItem.itemId,
      itemName: updatedItem.itemName,
      description: updatedItem.description,
      category: updatedItem.category,
      price: parseFloat(updatedItem.price),
      image: updatedItem.image,
    };

    console.log('Updated data:', updatedData);

    const token = localStorage.getItem('token');

    axios
      .put(
        `http://localhost:8080/registration/authorization/item/update/${selectedItem.itemId}`,
        updatedData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then((response) => {
        console.log('Update response:', response.data);
        setFilteredFoodItems((prevItems) =>
          prevItems.map((item) =>
            item.itemId === selectedItem.itemId ? response.data : item,
          ),
        );
        handleCloseModal();
      })
      .catch((error) => {
        console.error('Error updating item:', error);
        if (error.response) {
          console.error('Backend error:', error.response.data);
        }
      });
  };

  const handleViewDetails = (item) => {
    setSelectedItem(item);
    setIsDetailModalOpen(true);
  };

  const handleCloseDetailModal = () => {
    setIsDetailModalOpen(false);
    setSelectedItem(null);
  };

  const handleDeleteClick = (item) => {
    setItemToDelete(item);
    setIsDeleteModalOpen(true);
  };

  const handleDeleteModalClose = () => {
    setIsDeleteModalOpen(false);
    setItemToDelete(null);
  };

  const handleDeleteSubmit = () => {
    if (!itemToDelete) return;

    const token = localStorage.getItem('token');

    axios
      .delete(
        `http://localhost:8080/registration/authorization/item/delete/${itemToDelete.itemId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      )
      .then(() => {
        setFilteredFoodItems((prevItems) =>
          prevItems.filter((item) => item.itemId !== itemToDelete.itemId),
        );
        handleDeleteModalClose();
      })
      .catch((error) => {
        console.error('Error deleting item:', error);
      });
  };

  const handleAddToCart = async (itemId) => {
    console.log(sessionStorage.getItem('cartId')+'juu');

    const cartId = sessionStorage.getItem('cartId').slice(-5) ;
    try {
      const response = await axiosInstance.post(
        `http://localhost:8080/registration/authorization/addingitemtocart/${cartId}/${itemId}`,
        {
          username: localStorage.getItem('username'),
          itemId: itemId,
        },
      );
      if (response.status === 201) {
         
        alert('Item added to cart successfully!');
        navigate('/mycart')
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        alert('Unauthorized: Please log in to add items to the cart.');
      } else {
        alert('Error adding item to cart. Please try again.');
      }
    }
  };

  return (
    <>
      <div
        className="relative min-h-screen bg-cover bg-center p-8"
        style={{ backgroundImage: `url(${photo74})`, opacity: 1.2 }}
      >
        <div className="flex justify-between items-center mb-4">
          <Link to="/restaurantlist">
            <button
              type="button"
              className="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2"
            >
              Go Back
            </button>
          </Link>

          {isAdmin && (
            <Link to="/addfooditem">
              <button
                type="button"
                className="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2"
              >
                Add Item
              </button>
            </Link>
          )}

          <input
            type="text"
            value={searchTerm}
            onChange={handleSearchChange}
            placeholder="Search items"
            className="text-gray-700 border border-gray-300 rounded-lg px-4 py-2"
            style={{ marginLeft: 'auto' }}
          />
        </div>

        {filteredFoodItems.length === 0 && <p>No items found</p>}

        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {filteredFoodItems.map((item) => (
            <div
              key={item.itemId}
              className="bg-white shadow-md rounded-lg p-6"
            >
              <img
                src={item.image || 'default_image_url.jpg'}
                alt={item.itemName || 'Food Item'}
                className="h-40 w-full object-cover rounded-t-lg mb-4"
              />
              <h2 className="text-xl font-bold">
                {item.itemName || 'Unnamed Item'}
              </h2>
              <p className="text-gray-700 mb-4">
                {item.description || 'No description available'}
              </p>
              <p className="text-lg font-semibold text-gray-800">
                Price: ₹ {item.price}
              </p>

              <button
                className="mt-2 text-blue-600 hover:underline"
                onClick={() => handleViewDetails(item)} // View details button
              >
                See More Info
              </button>

              

              {!isAdmin && (
                <div className="mt-5">
                  <button
                    className="mt-2 text-white bg-green-600 hover:bg-green-800 px-4 py-2 rounded-lg"
                    onClick={() => handleAddToCart(item.itemId)}
                  >
                    Add to Cart
                  </button>
                </div>
              )}

              {isAdmin && (
                <div className="mt-5">
                  <button
                    className="mt-2 text-white bg-custom-clr hover:bg-green-700 px-4 py-2 rounded-lg"
                    onClick={() => handleUpdateItem(item)}
                  >
                    Update
                  </button>
                  <button
                    className="mt-2 text-white bg-red-600 hover:bg-red-700 px-4 py-2 rounded-lg ml-2"
                    onClick={() => handleDeleteClick(item)}
                  >
                    Delete
                  </button>
                </div>
              )}
            </div>
          ))}
        </div>
      </div>

      {/* Modal for updating item */}
      {isModalOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white rounded-lg p-6 max-w-lg w-full">
            <h2 className="text-2xl font-bold mb-4">Update Item</h2>

            <div className="mb-4">
              <label className="block text-sm font-bold mb-2">Item Name</label>
              <input
                type="text"
                name="itemName"
                value={updatedItem.itemName}
                onChange={handleInputChange}
                className="w-full border rounded px-4 py-2"
              />
            </div>

            <div className="mb-4">
              <label className="block text-sm font-bold mb-2">
                Description
              </label>
              <textarea
                name="description"
                value={updatedItem.description}
                onChange={handleInputChange}
                className="w-full border rounded px-4 py-2"
              />
            </div>

            <div className="mb-4">
              <label className="block text-sm font-bold mb-2">Category</label>
              <input
                type="text"
                name="category"
                value={updatedItem.category}
                onChange={handleInputChange}
                className="w-full border rounded px-4 py-2"
              />
            </div>

            <div className="mb-4">
              <label className="block text-sm font-bold mb-2">Price</label>
              <input
                type="number"
                name="price"
                value={updatedItem.price}
                onChange={handleInputChange}
                className="w-full border rounded px-4 py-2"
              />
            </div>

            <div className="mb-4">
              <label className="block text-sm font-bold mb-2">Image URL</label>
              <input
                type="text"
                name="image"
                value={updatedItem.image}
                onChange={handleInputChange}
                className="w-full border rounded px-4 py-2"
              />
            </div>

            <div className="flex justify-end">
              <button
                className="bg-red-600 text-white px-4 py-2 rounded mr-2"
                onClick={handleCloseModal}
              >
                Close
              </button>
              <button
                className="bg-green-600 text-white px-4 py-2 rounded"
                onClick={handleUpdateSubmit}
              >
                Update
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Modal for item details */}
      {isDetailModalOpen && selectedItem && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white rounded-lg p-6 max-w-lg w-full">
            <h2 className="text-2xl font-bold mb-4">Item details</h2>
            <img
              src={selectedItem.image || 'default_image_url.jpg'}
              alt={selectedItem.itemName}
              className="h-40 w-full object-cover rounded mb-4"
            />
            <p className="text-lg font-semibold">
              Item Id: {selectedItem.itemId}
            </p>
            <p className="text-lg font-semibold">
              Item Name: {selectedItem.itemName}
            </p>
            <p className="text-lg font-semibold">
              Description: {selectedItem.description}
            </p>
            <p className="text-lg font-semibold">
              Category: {selectedItem.category}
            </p>
            <p className="text-lg font-semibold">
              Price: ₹ {selectedItem.price}
            </p>
            <div className="flex justify-end">
              <button
                className="bg-black text-white px-4 py-2 rounded mt-4"
                onClick={handleCloseDetailModal}
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}

      {isDeleteModalOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white rounded-lg p-6 max-w-md w-full">
            <h2 className="text-xl font-bold mb-4">Confirm Delete</h2>
            <p>
              Are you sure you want to delete the item{' '}
              <strong>{itemToDelete?.itemName}</strong>?
            </p>
            <div className="mt-4 flex justify-end">
              <button
                className="bg-gray-500 text-white px-4 py-2 rounded mr-2"
                onClick={handleDeleteModalClose}
              >
                Cancel
              </button>
              <button
                className="bg-red-600 text-white px-4 py-2 rounded"
                onClick={handleDeleteSubmit}
              >
                Delete
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default RestaurantFoodItems;
