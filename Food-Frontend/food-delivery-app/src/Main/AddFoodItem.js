import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import photo23 from './photo23.jpg';

const AddFoodItem = () => {
  const [foodItem, setFoodItem] = useState({
    itemId: '',
    restaurantId: '',
    itemName: '',
    image: '',
    category: '',
    description: '',
    price: '',
  });

  const handleGoBack=()=>{
    navigate('/fooditems')
  }

  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const validateForm = () => {
    let isValid = true;
    let errors = {};

    if (!foodItem.itemId) {
      isValid = false;
      errors.itemId = 'Item ID is required';
    }

    if (!foodItem.restaurantId) {
      isValid = false;
      errors.restaurantId = 'Restaurant ID is required';
    }

    if (!foodItem.itemName) {
      isValid = false;
      errors.itemName = 'Item name is required';
    }

    if (!foodItem.image) {
      isValid = false;
      errors.image = 'Image is required';
    }

    if (!foodItem.category) {
      isValid = false;
      errors.category = 'Category is required';
    }

    if (!foodItem.description) {
      isValid = false;
      errors.description = 'Description is required';
    }

    if (!foodItem.price) {
      isValid = false;
      errors.price = 'Price is required';
    } else if (isNaN(foodItem.price)) {
      isValid = false;
      errors.price = 'Price must be a valid number';
    }

    setErrors(errors);
    return isValid;
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    const token = localStorage.getItem('token'); // Retrieve the token from localStorage
    axios
      .post('http://localhost:8080/registration/authorization/item/add', foodItem, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setFoodItem({
          itemId: '',
          restaurantId: '',
          itemName: '',
          image: '',
          category: '',
          description: '',
          price: '',
        });
        alert('Food Item Added Successfully');
        navigate('/fooditems'); // Navigate back to restaurant list or relevant page
      })
      .catch((error) => {
        console.error('Error adding food item:', error);
        setErrors({ submit: 'Failed to add food item: ' + (error.response?.data?.message || error.message) });
      });
  };

  const handleCancel = () => {
    setFoodItem({
      itemId: '',
      restaurantId: '',
      itemName: '',
      image: '',
      category: '',
      description: '',
      price: '',
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
            <h2 className="text-2xl font-bold mb-4 text-center">Add Food Item</h2>
            <form onSubmit={handleSubmit}>
              <div className="mb-4">
                <label className="block text-gray-700">Item ID</label>
                <input
                  type="text"
                  value={foodItem.itemId}
                  onChange={(e) => setFoodItem({ ...foodItem, itemId: e.target.value })}
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.itemId && <p className="text-red-500 text-sm">{errors.itemId}</p>}
              </div>

              <div className="mb-4">
                <label className="block text-gray-700">Restaurant ID</label>
                <input
                  type="text"
                  value={foodItem.restaurantId}
                  onChange={(e) => setFoodItem({ ...foodItem, restaurantId: e.target.value })}
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.restaurantId && <p className="text-red-500 text-sm">{errors.restaurantId}</p>}
              </div>

              <div className="mb-4">
                <label className="block text-gray-700">Item Name</label>
                <input
                  type="text"
                  value={foodItem.itemName}
                  onChange={(e) => setFoodItem({ ...foodItem, itemName: e.target.value })}
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.itemName && <p className="text-red-500 text-sm">{errors.itemName}</p>}
              </div>

              <div className="mb-4">
                <label className="block text-gray-700">Image</label>
                <input
                  type="text"
                  value={foodItem.image}
                  onChange={(e) => setFoodItem({ ...foodItem, image: e.target.value })}
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.image && <p className="text-red-500 text-sm">{errors.image}</p>}
              </div>

              <div className="mb-4">
                <label className="block text-gray-700">Category</label>
                <input
                  type="text"
                  value={foodItem.category}
                  onChange={(e) => setFoodItem({ ...foodItem, category: e.target.value })}
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.category && <p className="text-red-500 text-sm">{errors.category}</p>}
              </div>

              <div className="mb-4">
                <label className="block text-gray-700">Description</label>
                <input
                  type="text"
                  value={foodItem.description}
                  onChange={(e) => setFoodItem({ ...foodItem, description: e.target.value })}
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.description && <p className="text-red-500 text-sm">{errors.description}</p>}
              </div>

              <div className="mb-4">
                <label className="block text-gray-700">Price</label>
                <input
                  type="text"
                  value={foodItem.price}
                  onChange={(e) => setFoodItem({ ...foodItem, price: e.target.value })}
                  className="w-full p-2 border border-gray-300 rounded-lg"
                />
                {errors.price && <p className="text-red-500 text-sm">{errors.price}</p>}
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
                {/* <Link to="/fooditems"> */}
                  <button
                    type="button"
                    onClick={handleGoBack}
                    className="bg-red-600 text-white px-4 py-2 rounded-md"
                  >
                    Go Back
                  </button>
                {/* </Link> */}
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default AddFoodItem;
