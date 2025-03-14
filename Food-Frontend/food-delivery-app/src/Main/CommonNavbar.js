import React from 'react';
import logo from './blackfoodlogoforcommonnavbar.png';
import './CommonNavbar.css';
import { Link } from 'react-router-dom';

const CommonNavbar = ({ isAdmin, onLogout }) => {
  return (
    <>
      <nav className="bg-black border-gray-200">
        <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
          <div className="flex items-center space-x-3">
            <img src={logo} className="h-8" alt="Eat Express Logo" />
            <span className="self-center text-2xl font-semibold whitespace-nowrap text-white mycustompadding">
              Eat Express
            </span>
            <ul className="font-medium flex flex-row space-x-5 ml-40"> {/* Adjusted the margin */}
              {/* Conditionally render links based on isAdmin */}
              {isAdmin ? (
                <>
                  <li>
                    <Link to='/restaurantlist'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                      aria-current="page"
                    >
                      Restaurant List
                    </Link>
                  </li>
                  <li>
                    <Link to='/fooditems'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      Food Items
                    </Link>
                  </li>
                  <li>
                    <Link to='/orderslist'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      Orders List
                    </Link>
                  </li>
                  <li>
                    <Link to='/userlist'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      User List
                    </Link>
                  </li>
                  <li>
                    <Link to='/paymentinformation'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      Payments
                    </Link>
                  </li>
                  <li>
                    <Link to='/admininfo'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      Admin Info
                    </Link>
                  </li>
                </>
              ) : (
                <>
                  <li>
                    <Link to='/restaurantlist'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                      aria-current="page"
                    >
                      Restaurants
                    </Link>
                  </li>
                  <li>
                    <Link to='/fooditems'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      Food Items
                    </Link>
                  </li>
                  <li>
                    <Link to='/mycart'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      My Cart
                      
                    </Link>
                  </li>
                  <li>
                    <Link to='/myorders'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      My Orders
                    </Link>
                  </li>
                  <li>
                    <Link to='/userinfo'
                      className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                    >
                      User Info
                    </Link>
                  </li>
                </>
              )}
              {/* Logout option for both User and Admin */}
              <li>
                <button
                  onClick={onLogout}
                  className="block py-2 px-3 text-white rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
                >
                  Logout
                </button>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
};

export default CommonNavbar;
