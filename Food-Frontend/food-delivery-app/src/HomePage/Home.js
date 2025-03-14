import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

const Home = () => {
  return (
    <>
      <div className="hero">
        <video autoPlay loop muted playsInline className="back-video">
          <source src="/Home-Background-video.mp4" type="video/mp4" />
        </video>

        <div className="content">
          <h1>Eat Express</h1>
          <p className="paraofheading text-10xl font-bold text-center">
            Delicious meals delivered to your door.........!
          </p>
          <br />
          <Link to="/login">
            <button className="relative inline-flex items-center justify-center px-6 py-3.5 mb-2 me-4 text-base font-medium text-white-900 rounded-lg group bg-gradient-to-br from-purple-600 to-blue-500 group-hover:from-purple-600 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800">
              Login
            </button>
          </Link>
          <Link to="/signup">
            <button
              className="relative inline-flex items-center justify-center px-6 py-3.5 mb-2 ms-4 
              text-base font-medium text-gray-900 
		  rounded-lg group bg-gradient-to-br from-purple-500 to-pink-500 group-hover:from-purple-500 group-hover:to-pink-500 hover:text-white 
		  dark:text-white focus:ring-4 focus:outline-none focus:ring-purple-200 dark:focus:ring-purple-800"
            >
              Sign Up
            </button>
          </Link>
        </div>
      </div>
    </>
  );
};

export default Home;
