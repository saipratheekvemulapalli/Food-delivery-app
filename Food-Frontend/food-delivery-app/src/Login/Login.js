import React, { useState } from 'react';
import 'flowbite';
import logo from './logo.png';
import './Login.css';
import LoginPageBG from './LoginPageBG.jpg';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Login = ({ onLogin }) => {
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    role: '',
    email: '',
    phoneNumber: '',
    country: '',
  });

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/registration/signin', {
        username,
        password,
      });
  
      const roles = response.data.roles;
      const token = response.data.token;
      
      localStorage.setItem('token', token);
      localStorage.setItem('username', username);
      
      const isAdmin = roles.includes('Admin');
      onLogin(isAdmin, username);
  
      // Fetch user details to get phone number
      const userDetailsResponse = await axios.get(`http://localhost:8080/registration/authorization/getbyUsername/${username}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      
      const phoneNumber = userDetailsResponse.data.phoneNumber;
      sessionStorage.setItem('cartId', phoneNumber);
  
      navigate('/restaurantlist');
    } catch (error) {
      if (error.response) {
        const errorMessage = error.response.data?.message;
        if (errorMessage?.includes("doesn't exist")) {
          setErrorMessage('Username is not there in the database');
        } else if (errorMessage?.includes("Please check your password")) {
          setErrorMessage('Password is incorrect');
        } else {
          setErrorMessage('An unexpected error occurred. Please try again.');
        }
      } else {
        setErrorMessage('Network error. Please check your connection.');
      }
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
                  <Link to='/signup'
                    className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                  >
                    Register
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
          <h1 className="text-3xl font-bold text-white mb-2">Welcome!</h1>
          <p className="text-sm text-white mb-5">Log in to your Account</p>
          <form className="w-96 bg-slate-300 p-8 rounded shadow-lg space-y-4" onSubmit={handleLogin}>
      <div>
        <label htmlFor="username" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
          Username
        </label>
        <input
          type="text"
          id="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
          placeholder="Enter your username"
          required
        />
      </div>
      <div>
        <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
          Password
        </label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white"
          placeholder="Enter your password"
          required
        />
      </div>
      {errorMessage && <p className="text-red-500">{errorMessage}</p>}
      <button
        type="submit"
        className="focus:outline-none text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
      >
        Login
      </button>
    </form>
        </div>
      </div>
    </>
  );
};

export default Login;
