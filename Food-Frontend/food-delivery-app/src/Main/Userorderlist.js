import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserOrderList = () => {
  const [orderData, setOrderData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const userName = localStorage.getItem('username');

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/registration/authorization/viewOrderByName/${userName}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
        });
        setOrderData(response.data);
        setFilteredData(response.data);
      } catch (error) {
        console.error('Error fetching order details:', error);
      }
    };
    fetchOrders();
  }, [userName]);

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const day = date.getDate();
    const month = date.getMonth() + 1;
    const year = date.getFullYear();
    return `${day}-${month}-${year}`;
  };

  const handleSearch = (e) => {
    const searchTerm = e.target.value.toLowerCase();
    setSearchTerm(searchTerm);

    const filteredOrders = orderData.filter((order) =>
      order.orderName.toLowerCase().includes(searchTerm) ||
      order.city.toLowerCase().includes(searchTerm) ||
      order.state.toLowerCase().includes(searchTerm)
    );
    setFilteredData(filteredOrders);
  };

  
  

  return (
    <div
      className="relative min-h-screen bg-cover bg-center p-8"
      style={{ backgroundImage: `url('https://coolbackgrounds.io/images/backgrounds/index/compute-ea4c57a4.png')`, opacity: 1.2 }}
    >
      <div className="container mx-auto my-8">
        <h2 className="text-2xl font-semibold mb-4 text-center text-white">My Orders</h2>

        {/* Search Bar */}
        <div className="mb-4 text-center">
          <input
            type="text"
            placeholder="Search Orders"
            value={searchTerm}
            onChange={handleSearch}
            className="px-4 py-2 border rounded-lg shadow-sm w-full max-w-md"
          />
        </div>

        {/* Scrollable Table */}
        <div className="overflow-x-auto max-h-[600px]">
          <table className="min-w-full bg-white border border-gray-200" style={{ maxWidth: '1200px' }}>
            <thead>
              <tr className="bg-gray-100 border-b">
                <th className="px-4 py-3 text-left whitespace-nowrap">Order Date</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Order ID</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Order Name</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Order Status</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Amount Paid</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Order Details</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Order Instructions</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Mobile Number</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Address</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">City</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">State</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">Pincode</th>
                <th className="px-4 py-3 text-left whitespace-nowrap">User Name</th>
              </tr>
            </thead>
            <tbody>
              {filteredData.map((order, index) => (
                <tr key={index} className="border-b">
                  <td className="px-4 py-3 whitespace-nowrap">{formatDate(order.orderDate)}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.orderId}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.orderName.toUpperCase()}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.orderStatus}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.cost}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.orderDetails}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.orderInstructions}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.phoneNumber}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.address}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.city}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.state}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{order.pincode}</td>
                  <td className="px-4 py-3 whitespace-nowrap">{userName}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default UserOrderList;
