import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UsersList = () => {
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  const token = localStorage.getItem('token');
  
  useEffect(() => {
    // Fetching users from the backend API
    axios.get('http://localhost:8080/registration/authorization/getAllUsers', {
        headers: {
          Authorization: `Bearer ${token}`, 
        }
      })
      .then(response => {
        setUsers(response.data); // Assuming the response data is an array of users
      })
      .catch(error => {
        console.error("There was an error fetching the users!", error);
      });
  }, []);

  // Filter users based on the search term
  const filteredUsers = users.filter((user) => {
    return (
      user.username.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  return (
    <>
      <div
        className="relative min-h-screen bg-cover bg-center p-8"
        style={{ backgroundImage: `url(${'https://coolbackgrounds.io/images/backgrounds/index/compute-ea4c57a4.png'})`, opacity: 1.2 }}
      >
        <div className="flex items-center justify-between mb-4">
          <div className="flex-1 text-3xl font-bold text-white text-center">
            Users Information
          </div>
          
          <input
            type="text"
            placeholder="Search users"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500"
          />
        </div>

        <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
          <table className="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
              <tr>
                <th scope="col" className="px-6 py-3">Username</th>
                <th scope="col" className="px-6 py-3">Email</th>
                <th scope="col" className="px-6 py-3">Phone No</th>
                <th scope="col" className="px-6 py-3">Country</th>
                <th scope="col" className="px-6 py-3">Role</th>
              </tr>
            </thead>
            <tbody>
              {filteredUsers.length > 0 ? (
                filteredUsers.map((user) => (
                  <tr key={user.id} className="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                    <th scope="row" className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                      {user.username}
                    </th>
                    <td className="px-6 py-4">{user.email}</td>
                    <td className="px-6 py-4">{user.phoneNumber}</td>
                    <td className="px-6 py-4">{user.country}</td>
                    <td className="px-6 py-4">{user.role}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="5" className="px-16 py-14 text-center text-white" style={{fontSize:'24px'}}>No users found</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default UsersList;
