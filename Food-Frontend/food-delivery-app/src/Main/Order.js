import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Order = () => {
  const navigate = useNavigate();
  const [orderDetails, setOrderDetails] = useState({
    orderName: '',
    username: localStorage.getItem('username'),
    phoneNumber: '',
    address: '',
    pincode: '',
    city: '',
    state: '',
    orderInstructions: '',
  });

  const username = localStorage.getItem('username');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setOrderDetails((prevDetails) => ({
      ...prevDetails,
      [name]: value,
    }));
  };

  const cartId = sessionStorage.getItem('cartId')?.slice(-5);
  

  

  const handleSubmit = async () => {
    if (orderDetails.orderName.length < 3 || orderDetails.orderName.length > 30 ) {
      alert('Please fill in Order Name.');
      return;
    }
    if (!/^\d{10}$/.test(orderDetails.phoneNumber)) {
      alert('Please fill in Phone Number.');
      return;
    }
    if (orderDetails.address.length<4 ) {
      alert('Please fill in Address.');
      return;
    }
    if (!/^\d{6}$/.test(orderDetails.pincode) ) {
      alert('Please fill in Pincode.');
      return;
    }
    if (!orderDetails.city ) {
      alert('Please fill in City.');
      return;
    }
    if (!orderDetails.state ) {
      alert('Please fill in State.');
      return;
    }
    try {
      const response = await axios.post(
        `http://localhost:8080/registration/authorization/order/${cartId}`,
        orderDetails,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
        },
      );

      // Assuming response contains the order cost and order ID in string format
      const responseString = response.data.toString();
      const id = responseString.slice(-4); // Get last four characters as order ID
      const cost = responseString.slice(0, -4); // Get cost portion

      sessionStorage.setItem('totalCost', cost);
      sessionStorage.setItem('orderId', id);

      alert('Order has been placed, please complete the payment.');

      // Clear cart
      await axios.delete(
        `http://localhost:8080/registration/authorization/deleteCart/${cartId}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
        },
      );

      navigate('/payment');
    } catch (error) {
      console.error('Error placing the order:', error);
      alert('There was an issue placing the order. Please try again.');
    }
  };

  return (
    <div
        className="relative min-h-screen bg-cover bg-center p-8"
        style={{ backgroundImage: `url(${'https://coolbackgrounds.io/images/backgrounds/index/compute-ea4c57a4.png'})`, opacity: 1.2 }}
      >
    <div className="flex justify-center mt-10">
      <div className="max-w-lg w-full p-6 bg-white border border-gray-200 rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold text-center mb-6">
          Order Details
        </h2>

        <form onSubmit={(e) => e.preventDefault()}>
          <div className="mb-4">
            <label className="block mb-1 font-medium">Order Name:</label>
            <input
              type="text"
              name="orderName"
              value={orderDetails.orderName}
              onChange={handleChange}
              required
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 font-medium">User Name:</label>
            <input
              type="text"
              name="orderName"
              value={username.toLocaleUpperCase()}
              //onChange={handleChange}
              required
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 font-medium">Phone Number:</label>
            <input
              type="text"
              name="phoneNumber"
              value={orderDetails.phoneNumber}
              onChange={handleChange}
              required
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 font-medium">Address:</label>
            <input
              type="text"
              name="address"
              value={orderDetails.address}
              onChange={handleChange}
              required
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 font-medium">Pincode:</label>
            <input
              type="text"
              name="pincode"
              value={orderDetails.pincode}
              onChange={handleChange}
              required
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 font-medium">City:</label>
            <input
              type="text"
              name="city"
              value={orderDetails.city}
              onChange={handleChange}
              required
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 font-medium">State:</label>
            <input
              type="text"
              name="state"
              value={orderDetails.state}
              onChange={handleChange}
              required
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <div className="mb-4">
            <label className="block mb-1 font-medium">
              Order Instructions:
            </label>
            <textarea
              name="orderInstructions"
              value={orderDetails.orderInstructions}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-green-400"
            />
          </div>

          <button
            onClick={handleSubmit}
            className="w-full py-2 bg-green-600 text-white rounded hover:bg-green-700 focus:ring-4 focus:ring-green-300 transition ease-in-out duration-200"
          >
            Place Order
          </button>
        </form>
      </div>
    </div>
    </div>
  );
};

export default Order;
