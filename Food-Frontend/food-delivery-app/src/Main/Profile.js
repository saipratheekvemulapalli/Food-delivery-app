import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Profile = ({ username }) => {
  const [profileData, setProfileData] = useState({
    username: '',
    email: '',
    phoneNumber: '',
    country: '',
    password: ''
  });

  const [isModalOpen, setIsModalOpen] = useState(false);

  useEffect(() => {
    console.log('Profile component mounted with username:', username);

    const fetchProfileData = async () => {
      if (username) {
        try {
          console.log('Fetching profile for username:', username);
          const response = await axios.get(
            `http://localhost:8080/registration/authorization/getbyUsername/${username}`
          );
          setProfileData(response.data);
        } catch (error) {
          console.error('Error fetching profile data:', error);
        }
      } else {
        console.error('Username is undefined');
      }
    };

    fetchProfileData();
  }, [username]);

  const handleUpdate = async () => {
    try {
      // Send the updated profile data excluding the password
      const { password, ...updatedData } = profileData;
      await axios.put(`http://localhost:8080/registration/authorization/updatetheusername/${username}`, updatedData);
      alert('Profile updated successfully!');
      setIsModalOpen(false); // Close the modal after successful update
    } catch (error) {
      console.error('Error updating profile data:', error);
      alert('Failed to update profile.');
    }
  };

  return (
    <>
    <div
        className="relative min-h-screen bg-cover bg-center p-8"
        style={{ backgroundImage: `url(${'https://images.rawpixel.com/image_800/czNmcy1wcml2YXRlL3Jhd3BpeGVsX2ltYWdlcy93ZWJzaXRlX2NvbnRlbnQvbHIvdjEwMTYtYy0wOF8xLWtzaDZtemEzLmpwZw.jpg'})`, opacity: 1.2 }}
      >
        <div className="max-w-md mx-auto p-6 bg-white rounded-lg shadow-md mt-8">
      <h2 className="text-2xl font-semibold text-center mb-6">Profile</h2>
      <div className="space-y-4">
        <div>
          <label className="block text-gray-700">Username</label>
          <input
            type="text"
            value={profileData.username}
            className="w-full p-2 border border-gray-300 rounded-lg"
            readOnly
          />
        </div>
        <div>
          <label className="block text-gray-700">Password</label>
          <input
            type="password" // Changed to password type
            value={profileData.password}
            className="w-full p-2 border border-gray-300 rounded-lg"
            readOnly
          />
        </div>
        <div>
          <label className="block text-gray-700">Email</label>
          <input
            type="email"
            value={profileData.email}
            className="w-full p-2 border border-gray-300 rounded-lg"
            readOnly
          />
        </div>
        <div>
          <label className="block text-gray-700">Phone Number</label>
          <input
            type="text"
            value={profileData.phoneNumber}
            className="w-full p-2 border border-gray-300 rounded-lg"
            readOnly
          />
        </div>
        <div>
          <label className="block text-gray-700">Country</label>
          <input
            type="text"
            value={profileData.country}
            className="w-full p-2 border border-gray-300 rounded-lg"
            readOnly
          />
        </div>
        <button
          className="mt-4 px-4 py-2 bg-cyan-700 text-white rounded"
          onClick={() => setIsModalOpen(true)}
        >
          Update
        </button>
      </div>

      {/* Modal */}
      {isModalOpen && (
        <div className="fixed inset-0 bg-gray-800 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-md w-1/2">
            <h2 className="text-lg font-semibold mb-4">Update Profile</h2>
            <div>
              <label className="block text-gray-700">Username</label>
              <input
                type="text"
                value={profileData.username}
                onChange={(e) => setProfileData({ ...profileData, username: e.target.value })}
                className="w-full p-2 border border-gray-300 rounded-lg"
              />
            </div>
            <div>
              <label className="block text-gray-700">Password</label>
              <input
                type="password"
                value={profileData.password}
                className="w-full p-2 border border-gray-300 rounded-lg"
                disabled // Disable the password field
              />
            </div>
            <div>
              <label className="block text-gray-700">Email</label>
              <input
                type="email"
                value={profileData.email}
                onChange={(e) => setProfileData({ ...profileData, email: e.target.value })}
                className="w-full p-2 border border-gray-300 rounded-lg"
              />
            </div>
            <div>
              <label className="block text-gray-700">Phone Number</label>
              <input
                type="text"
                value={profileData.phoneNumber}
                onChange={(e) => setProfileData({ ...profileData, phoneNumber: e.target.value })}
                className="w-full p-2 border border-gray-300 rounded-lg"
              />
            </div>
            <div>
              <label className="block text-gray-700">Country</label>
              <input
                type="text"
                value={profileData.country}
                onChange={(e) => setProfileData({ ...profileData, country: e.target.value })}
                className="w-full p-2 border border-gray-300 rounded-lg"
              />
            </div>
            <div className="mt-4 flex justify-end">
              <button className="mr-2 px-4 py-2 bg-gray-300 rounded" onClick={() => setIsModalOpen(false)}>Close</button>
              <button className="px-4 py-2 bg-green-600 text-white rounded" onClick={handleUpdate}>Update</button>
            </div>
          </div>
        </div>
      )}
    </div>
      </div>
    
    </>
    
  );
};

export default Profile;
