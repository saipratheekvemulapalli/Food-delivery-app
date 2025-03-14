import React, { useState } from 'react';
import 'flowbite';
import logo from './logo.png';
import './Registration.css';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const RegistrationPage = ({ onRegister }) => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    role: '',
    email: '',
    phoneNumber: '',
    country: '',
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({
      ...formData,
      [id]: value,
    });
  };

  //  original one 

  
  // const handleRegister = async (event) => {
  //   event.preventDefault();
  //   try {
  //     const response = await axios.post(
  //       'http://localhost:8080/register/addUser',
  //       formData
  //     );
  //     sessionStorage.setItem('cartId',formData.phoneNumber);
  //     if (response.status === 200) {
  //       alert('Data added successfully!');
  //       onRegister(); // Call the function to update the login state
  //       navigate('/login'); // Redirect to the login page
  //     } else {
  //       alert('Registration failed. Please try again.'); // In case of non-200 status
  //     }
  //   } catch (error) {
  //     //console.error('Registration failed:', error);
  //     navigate('/login');
  //   }
  // };


  const handleRegister = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post(
        'http://localhost:8080/register/addUser',
        formData
      );
      sessionStorage.setItem('cartId', formData.phoneNumber);
      if (response.status === 200) {
        alert('Data added successfully!');
        onRegister(); // Update login state
        navigate('/login'); // Redirect to login page
      } else {
        alert('Registration failed. Please try again.');
      }
    } catch (error) {
      if (error.response && error.response.status) {
        alert('Failed to register. Please try again.'); // Display user-friendly error
      }
      navigate('/login');
    }
  };

  
  return (
    <>
      <div>
      <nav className="bg-gray-200 border-gray-200 dark:bg-gray-300 dark:border-gray-100">
          <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
            <Link to="/">
            <div
              href="/"
              className="flex items-center space-x-3 rtl:space-x-reverse"
            >
              <img src={logo} className="h-8" alt="Eat Express Logo" />
              <span className="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">
                Eat Express
              </span>
            </div>
            </Link>
            <button
              data-collapse-toggle="navbar-dropdown"
              type="button"
              className="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
              aria-controls="navbar-dropdown"
              aria-expanded="false"
            >
              <span className="sr-only">Open main menu</span>
              <svg
                className="w-5 h-5"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 17 14"
              >
                <path
                  stroke="currentColor"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M1 1h15M1 7h15M1 13h15"
                />
              </svg>
            </button>
            <div
              className="hidden w-full md:block md:w-auto"
              id="navbar-dropdown"
            >
              <ul className="flex flex-col font-medium p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0 md:bg-gray-200 dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                <li>
                  <Link to='/'
                    className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                  >
                    Home
                  </Link>
                </li>
                <li>
                  <Link to='/login'
                    className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                  >
                    Login
                  </Link>
                </li>
                <li>
                  <a href='https://www.gmail.com' target='_blank'
                    className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                  >
                    Support Team
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>

      <div className="login-bg flex items-center justify-center min-h-screen bg-gray-100 dark:bg-gray-900">
        <div className="flex flex-col items-center">
          <h1 className="text-2xl font-bold mt-4 text-white">
            Join Us: Sign Up Today!
          </h1>

          <form
            onSubmit={handleRegister}
            className="bg-white dark:bg-gray-800 shadow-md rounded-lg p-8 mt-8 max-w-2xl w-full"
          >
            <div className="grid gap-6 mb-6">
              {/* Username Field */}
              <div className="flex items-center">
                <label
                  htmlFor="username"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white w-1/3"
                >
                  Username
                </label>
                <input
                  type="text"
                  id="username"
                  value={formData.username}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-2/3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Enter Your Username"
                  required
                />
              </div>

              {/* Password Field */}
              <div className="flex items-center">
                <label
                  htmlFor="password"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white w-1/3"
                >
                  Password
                </label>
                <input
                  type="password"
                  id="password"
                  value={formData.password}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-2/3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Enter your Password"
                  required
                />
              </div>

              {/* Role Field */}
              <div className="flex items-center">
                <label
                  htmlFor="role"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white w-1/3"
                >
                  Role
                </label>
                <input
                  type="text"
                  id="role"
                  value={formData.role}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-2/3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Admin or User"
                  required
                />
              </div>

              {/* Email Field */}
              <div className="flex items-center">
                <label
                  htmlFor="email"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white w-1/3"
                >
                  Email
                </label>
                <input
                  type="email"
                  id="email"
                  value={formData.email}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-2/3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Enter Your Email"
                  required
                />
              </div>

              {/* Phone Number Field */}
              <div className="flex items-center">
                <label
                  htmlFor="phoneNumber"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white w-1/3"
                >
                  Phone number
                </label>
                <input
                  type="tel"
                  id="phoneNumber"
                  value={formData.phoneNumber}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-2/3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Enter Your Phone Number"
                  required
                />
              </div>

              {/* Country Field */}
              <div className="flex items-center">
                <label
                  htmlFor="country"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white w-1/3"
                >
                  Country
                </label>
                <input
                  type="text"
                  id="country"
                  value={formData.country}
                  onChange={handleChange}
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-2/3 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Enter Your Country"
                  required
                />
              </div>

              {/* Terms and Conditions */}
              <div className="flex items-center">
                <input
                  type="checkbox"
                  id="terms"
                  className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                  required
                />
                <label
                  htmlFor="terms"
                  className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >
                  I accept the{' '}
                  <span
                    className="text-blue-600 hover:underline dark:text-blue-500"
                  >
                    Terms and Conditions
                  </span>
                </label>
              </div>
            </div>

            <button
              type="submit"
              className="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
            >
              Register
            </button>
          </form>
        </div>
      </div>
    </>
  );
};

export default RegistrationPage;
