import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import photo23 from './photo23.jpg';
import { useNavigate } from 'react-router-dom';

const AddRestaurant = () => {
  const [restaurant, setRestaurant] = useState({
    restaurantId: '',
    restaurantName: '',
    rating: '',
    type: '',
    location: '',
  });

  const handleGoBack=()=>{
    navigate('/restaurantlist')
  }

  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const validateForm = () => {
    let isValid = true;
    let errors = {};

    if (!restaurant.restaurantId) {
      isValid = false;
      errors.restaurantId = 'Restaurant ID is required';
    }

    if (!restaurant.restaurantName) {
      isValid = false;
      errors.restaurantName = 'Restaurant name is required';
    }

    if (!restaurant.rating) {
      isValid = false;
      errors.rating = 'Rating is required';
    } else if (!Number.isInteger(Number(restaurant.rating))) {
      isValid = false;
      errors.rating = 'Rating must be an integer';
    }

    if (!restaurant.type) {
      isValid = false;
      errors.type = 'Food type is required';
    }

    if (!restaurant.location) {
      isValid = false;
      errors.location = 'Location is required';
    }

    setErrors(errors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return; // If validation fails, don't proceed with the submission.
    }

    const token = localStorage.getItem('token'); // Retrieve the token from localStorage
    axios
      .post('http://localhost:8080/registration/authorization/addRestaurant', restaurant, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        alert("restaurant added successfully")
        navigate('/restaurantlist');
        setRestaurant({
          restaurantId: '',
          restaurantName: '',
          rating: '',
          type: '',
          location: '',
        });
      })
      .catch((error) => {
        console.error('Error adding restaurant:', error);
        setErrors({ submit: 'Failed to add restaurant: ' + (error.response?.data?.message || error.message) });
      });
  };

  const handleCancel = () => {
    setRestaurant({
      restaurantId: '',
      restaurantName: '',
      rating: '',
      type: '',
      location: '',
    });
    setErrors({});
  };

  return (
    <>
      <div
        className="relative min-h-screen bg-cover bg-center p-8"
        style={{ backgroundImage: `url(${photo23})`, opacity: 1.0 }}
      >
        <div className="flex justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-md">
            <h2 className="text-2xl font-bold mb-4 text-center">Add Restaurant</h2>
            <form onSubmit={handleSubmit}>
              <div className="mb-4">
                <label className="block text-gray-700">Restaurant Id</label>
                <input
                  type="text"
                  value={restaurant.restaurantId}
                  placeholder='Enter restaurantId'
                  onChange={(e) =>
                    setRestaurant({ ...restaurant, restaurantId: e.target.value })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.restaurantId && <p className="text-red-500 text-sm">{errors.restaurantId}</p>}
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Restaurant Name</label>
                <input
                  type="text"
                  value={restaurant.restaurantName}
                  placeholder='Enter restaurantName'
                  onChange={(e) =>
                    setRestaurant({ ...restaurant, restaurantName: e.target.value })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.restaurantName && <p className="text-red-500 text-sm">{errors.restaurantName}</p>}
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Rating</label>
                <input
                  type="text"
                  value={restaurant.rating}
                  placeholder='Enter rating'
                  onChange={(e) =>
                    setRestaurant({ ...restaurant, rating: e.target.value })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.rating && <p className="text-red-500 text-sm">{errors.rating}</p>}
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Food Type</label>
                <input
                  type="text"
                  value={restaurant.type}
                  placeholder='Enter Type of Food'
                  onChange={(e) =>
                    setRestaurant({ ...restaurant, type: e.target.value })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.type && <p className="text-red-500 text-sm">{errors.type}</p>}
              </div>
              <div className="mb-4">
                <label className="block text-gray-700">Location</label>
                <input
                  type="text"
                  value={restaurant.location}
                  placeholder='Enter Location'
                  onChange={(e) =>
                    setRestaurant({ ...restaurant, location: e.target.value })
                  }
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.location && <p className="text-red-500 text-sm">{errors.location}</p>}
              </div>
              {errors.submit && <p className="text-red-500 text-sm">{errors.submit}</p>}
              <div className="flex justify-between mt-6">
                <button
                  type="submit"
                  className="bg-green-800 text-white px-4 py-2 rounded-md"
                >
                  Submit
                </button>
                <button
                  type="button"
                  onClick={handleCancel}
                  className="bg-gray-500 text-white px-4 py-2 rounded-md"
                >
                  Cancel
                </button>
                  <button
                    type="button"
                    onClick={handleGoBack}
                    className="bg-red-600 text-white px-4 py-2 rounded-md"
                  >
                    Go Back
                  </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default AddRestaurant;