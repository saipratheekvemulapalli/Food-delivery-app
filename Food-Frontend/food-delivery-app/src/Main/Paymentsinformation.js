import React, { useState, useEffect } from 'react';
import axios from 'axios';

const PaymentsInformation = () => {
  const [payments, setPayments] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const token = localStorage.getItem('token');

  const fetchPayments = async () => {
    try {
      const response = await axios.get(
        'http://localhost:8080/registration/authorization/getallpayment',
        {
          headers: {
            Authorization: `Bearer ${token}`, // Use token for authorization
          },
        }
      );
  
      const sortedPayments = response.data.sort(
        (a, b) => new Date(b.paymentDate) - new Date(a.paymentDate)
      );
  
      setPayments(sortedPayments);
    } catch (error) {
      console.error('Error fetching payments:', error);
    }
  };
  
 
  

  useEffect(() => {
    fetchPayments();
  }, []);

  const handleDeleteClick = (id) => {
    axios
      .delete(`http://localhost:8080/registration/authorization/deletePayment/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        setPayments(payments.filter((payment) => payment.transactionId !== id));
        alert('Payment deleted successfully.');
      })
      .catch((error) => {
        console.error('Error deleting payment:', error);
      });
  };

  const updatePaymentStatus = async (id) => {
    try {
      await axios.put(
        `http://localhost:8080/registration/authorization/updatePaymentSuccess/${id}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`, // Include token in the headers
          },
        }
      );
      alert('Payment status updated to success!');
      fetchPayments(); 
    } catch (error) {
      console.error('Error updating payment status:', error);
      alert('Failed to update payment status');
    }
  };

  // Filter payments based on search term (orderId, transactionId, username, or transactionStatus)
  const filteredPayments = payments.filter(
    (payment) =>
      payment.orderId.toString().includes(searchTerm) ||
      payment.transactionId.toString().includes(searchTerm) ||
      payment.username.toLowerCase().includes(searchTerm.toLowerCase()) ||
      payment.transactionStatus.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div
      className="relative min-h-screen bg-cover bg-center p-8"
      style={{
        backgroundImage: `url(${'https://coolbackgrounds.io/images/backgrounds/index/compute-ea4c57a4.png'})`,
        opacity: 1.2,
      }}
    >
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold text-white">Payments Information</h1>
        
        {/* Search bar */}
        <input
          type="text"
          placeholder="Search by Order ID, Transaction ID, Username, or Status"
          className="p-2 rounded border border-gray-300"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>
      
      <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-6 py-3">S.No</th>
              <th scope="col" className="px-6 py-3">Order ID</th>
              <th scope="col" className="px-6 py-3">Transaction ID</th>
              <th scope="col" className="px-6 py-3">Amount</th>
              <th scope="col" className="px-6 py-3">Payment Date</th>
              <th scope="col" className="px-6 py-3">Transaction Status</th>
              <th scope="col" className="px-6 py-3">Username</th>
              <th scope="col" className="px-6 py-3">Modify</th>
            </tr>
          </thead>
          <tbody>
            {filteredPayments.length > 0 ? (
              filteredPayments.map((payment, index) => (
                <tr key={payment.transactionId} className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                  <td className="px-6 py-4">{index + 1}</td>
                  <td className="px-6 py-4">{payment.orderId}</td>
                  <td className="px-6 py-4">{payment.transactionId}</td>
                  <td className="px-6 py-4">₹{payment.amount}</td>
                  <td className="px-6 py-4">
                    {new Date(payment.paymentDate).toLocaleDateString('en-GB')}
                  </td>
                  <td className="px-6 py-4">{payment.transactionStatus}</td>
                  <td className="px-6 py-4">{payment.username}</td>
                  <td className="px-6 py-4">
                      <button
                        type="button"
                        className="text-white bg-green-500 hover:bg-green-700 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2"
                        onClick={() => updatePaymentStatus(payment.transactionId)}
                      >
                        Update to Success
                      </button>
                      <button
                        type="button"
                        className="text-white bg-purple-500 hover:bg-purple-700 focus:outline-none focus:ring-4 focus:ring-purple-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2"
                        onClick={() => handleDeleteClick(payment.transactionId)}
                      >
                        Delete
                      </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="8" className="px-6 py-4 text-center">
                  No payment records found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default PaymentsInformation;
