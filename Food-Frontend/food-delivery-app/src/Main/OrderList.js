import React, { useState, useEffect } from 'react';
import axios from 'axios';

const OrderList = () => {
  const [orders, setOrders] = useState([]);
  const token = localStorage.getItem('token');
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    axios
      .get('http://localhost:8080/registration/authorization/viewAllOrders', {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        const sortedOrders = response.data.sort(
          (a, b) => new Date(b.orderDate) - new Date(a.orderDate)
        );
        setOrders(sortedOrders);
      })
      .catch((error) => {
        console.error('There was an error fetching the orders!', error);
      });
  }, [token]);
  

  const deleteOrder = (orderId) => {
    axios
      .delete(`http://localhost:8080/registration/authorization/cancelOrder/${orderId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        setOrders((prevOrders) => prevOrders.filter(order => order.orderId !== orderId));
        alert('Order deleted successfully');
      })
      .catch((error) => {
        console.error('There was an error deleting the order!', error);
      });
  };

 
const filteredOrders = orders.filter((order) => {
  return (
    (order.username && order.username.toLowerCase().includes(searchTerm.toLowerCase())) ||
    (order.orderName && order.orderName.toLowerCase().includes(searchTerm.toLowerCase()))
  );
});


  return (
    <>
      <div
        className="relative min-h-screen bg-cover bg-center p-8"
        style={{
          backgroundImage: `url(${'https://4kwallpapers.com/images/wallpapers/dark-background-abstract-background-network-3d-background-3840x2160-8324.png'})`,
          opacity: 1.2,
        }}
      >
        <div className="flex items-center justify-between mb-4">
          <div className="flex-1 text-3xl font-bold text-white text-center">
            Order Details
          </div>
          <input
            type="text"
            placeholder="Search orders"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
          <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400 mt-2">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
              <tr>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">S.no</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Order ID</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Order Date</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">User Name</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Order Name</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Order Status</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Order Details</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Phone Number</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Cost</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Address</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">City</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">State</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Pincode</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Order Instructions</th>
                <th scope="col" className="px-6 py-3 whitespace-nowrap">Action</th>
              </tr>
            </thead>
            <tbody>
              {filteredOrders.length > 0 ? (
                filteredOrders.map((order, index) => {
                  const formattedDate = new Date(order.orderDate).toLocaleDateString('en-GB');

                  return (
                    <tr key={order.id} className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                      <td className="px-6 py-4">{index + 1}</td>
                      <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                        {order.orderId}
                      </th>
                      <td className="px-6 py-4 whitespace-nowrap">{formattedDate}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.username}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.orderName}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.orderStatus}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{`${order.orderDetails} Restaurant: ${order.restaurantName} Item Price: ₹${order.itemPrice}`}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.phoneNumber}</td>
                      <td className="px-6 py-4 whitespace-nowrap">₹ {order.cost}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.address}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.city}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.state}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.pincode}</td>
                      <td className="px-6 py-4 whitespace-nowrap">{order.orderInstructions}</td>
                      <td className="px-6 py-4 whitespace-nowrap">
                        <button
                          type="button"
                          onClick={() => deleteOrder(order.orderId)} 
                          className="px-3 py-2 text-xs focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
                        >
                          Delete
                        </button>
                      </td>
                    </tr>
                  );
                })
              ) : (
                <tr>
                  <td colSpan="14" className="px-6 py-4 text-center">No orders found</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default OrderList;
