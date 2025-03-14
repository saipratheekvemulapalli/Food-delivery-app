import './App.css';
import Home from './HomePage/Home';
import 'flowbite';
import 'flowbite-react';
import RegistrationPage from './Registration/RegistrationPage';
import Login from './Login/Login';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import CommonNavbar from './Main/CommonNavbar';
import RestaurantList from './Main/RestaurantList';
import { useState, useEffect } from 'react';
import AddRestaurant from './Main/AddRestaurant';
import FoodItems from './Main/FoodItems';
import AddFoodItem from './Main/AddFoodItem';
import RestaurantFoodItems from './Main/RestaurantFoodItems';
import UsersList from './Main/UsersList';
import OrderList from './Main/OrderList';
import Profile from './Main/Profile';
import Cart from './Main/Cart';
import Order from './Main/Order';
import Payment from './Main/Payment';
import Userorderlist from './Main/Userorderlist';
import Paymentsinformation from './Main/Paymentsinformation';


function App() {
  // Initialize states from localStorage
  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    return localStorage.getItem('isLoggedIn') === 'true';
  });
  const [isAdmin, setIsAdmin] = useState(() => {
    return localStorage.getItem('isAdmin') === 'true';
  });

  const handleLogin = (isAdminValue, username,phoneNumber) => {
    console.log('User role:', isAdminValue); 
    
    setIsLoggedIn(true);
    setIsAdmin(isAdminValue);
    localStorage.setItem('isLoggedIn', 'true');
    localStorage.setItem('isAdmin', isAdminValue.toString());
    localStorage.setItem('username', username); // Save username to localStorage
    localStorage.setItem('cartId', phoneNumber)
  };
  
  
  

  // const handleLogout = () => {
  //   setIsLoggedIn(false);
  //   setIsAdmin(false);

  //   // Clear localStorage
  //   localStorage.removeItem('isLoggedIn');
  //   localStorage.removeItem('isAdmin');
  // };

  const handleLogout = () => {
    // Reset state in the application
    setIsLoggedIn(false);
    setIsAdmin(false);
  
    // Clear all relevant localStorage items
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('isAdmin');
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    localStorage.removeItem('cartId');
  };

  return (
    <Router>
      <div>
      {isLoggedIn && <CommonNavbar isAdmin={isAdmin} onLogout={handleLogout} />}

        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login onLogin={handleLogin} />} />
          <Route path="/signup" element={<RegistrationPage onRegister={handleLogin} />} />
          <Route
            path="/restaurantlist"
            element={isLoggedIn ? <RestaurantList isAdmin={isAdmin} /> : <Navigate to="/login" />}
          />
          <Route path='/fooditems' element={isLoggedIn ? <FoodItems isAdmin={isAdmin} />: <Navigate to="/login"/> } />
          <Route path="/restaurantfooditems" element={<RestaurantFoodItems isAdmin={isAdmin} />} />
          <Route path='/addrestaurant' element={isLoggedIn ? <AddRestaurant isAdmin={isAdmin}/> : <Navigate to='/login'/>} />
          <Route path='/addfooditem' element={isLoggedIn ? <AddFoodItem isAdmin={isAdmin}/> : <Navigate to='/login'/>} />
          <Route path='/userlist' element={isLoggedIn ? <UsersList isAdmin={isAdmin} />: <Navigate to="/login"/> } />
          <Route path='/orderslist' element={isLoggedIn ? <OrderList isAdmin={isAdmin} />: <Navigate to="/login"/> } />
          <Route 
  path='/admininfo' 
  element={isLoggedIn ? (
    <>
      {console.log('Username from localStorage:', localStorage.getItem('username'))}
      <Profile username={localStorage.getItem('username')} isAdmin={isAdmin} />
    </>
  ) : <Navigate to="/login"/>} 
/>
<Route 
  path='/userinfo' 
  element={isLoggedIn ? (
    <>
      {console.log('Username from localStorage:', localStorage.getItem('username'))}
      <Profile username={localStorage.getItem('username')} isAdmin={!isAdmin} />
    </>
  ) : <Navigate to="/login"/>} 
/>
<Route path="/mycart" element={isLoggedIn ? <Cart /> : <Navigate to="/login" />} />
<Route path="/order" element={isLoggedIn ? <Order /> : <Navigate to="/login" />} />
<Route path="/payment" element={isLoggedIn ? <Payment /> : <Navigate to="/login" />} />
<Route path="/myorders" element={isLoggedIn ? <Userorderlist /> : <Navigate to="/login" />} />
<Route path="/paymentinformation" element={isLoggedIn ? <Paymentsinformation /> : <Navigate to="/login" />} />




        </Routes>
      </div>
    </Router>
  );
}

export default App;
