// src/axiosInstance.js
import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/registration/authorization', // your base URL
  // You can add more configurations if needed, like headers
});

// Optionally add an interceptor if you need to handle tokens
axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

export default axiosInstance; // Make sure to export the instance
