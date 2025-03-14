import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Cart = () => {
  const navigate = useNavigate();
  const [cartItems, setCartItems] = useState([]);
  const [totalAmount, setTotalAmount] = useState(0);

  const cartId = sessionStorage.getItem('cartId')?.slice(-5);
  const token = localStorage.getItem('token');

  const fetchCartItems = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/registration/authorization/${cartId}`, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      console.log('hai'+cartId);
      const { items = [], totalPrice = 0 } = response.data || {};
      setCartItems(items);
      setTotalAmount(totalPrice);
    } catch (error) {
      console.error('Error fetching cart items:', error);
      setCartItems([]); // Ensure cartItems is an array even if fetch fails
      setTotalAmount(0);
    }
  };

  useEffect(() => {
    if (cartId) {
      fetchCartItems();
    }
  }, [cartId]);

  const handleIncreaseQuantity = async (itemId) => {
    try {
      await axios.post(
        `http://localhost:8080/registration/authorization/addingitemtocart/${cartId}/${itemId}`,
        { username: localStorage.getItem('username'), itemId },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      fetchCartItems();
    } catch (error) {
      console.error('Error increasing item quantity:', error);
    }
  };

  const handleDecreaseQuantity = async (itemId) => {
    try {
      await axios.put(`http://localhost:8080/registration/authorization/decreaseQuant/${itemId}/${cartId}`, null, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      fetchCartItems();  
    } catch (error) {
      console.error('Error decreasing item quantity:', error);
    }
  };

  const handleRemoveItem = async (itemId) => {
    try {
      await axios.put(`http://localhost:8080/registration/authorization/deleteItem/${cartId}/${itemId}`, null, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      fetchCartItems();
    } catch (error) {
      console.error('Error removing item from cart:', error);
    }
  };

  const handleClearCart = async () => {
    if(totalAmount!==0){
      try {
        await axios.delete(`http://localhost:8080/registration/authorization/deleteCart/${cartId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setCartItems([]);
        setTotalAmount(0);
        alert('Cart has been cleared successfully!');
      } catch (error) {
        console.error('Error clearing the cart:', error);
      }
    }
    else{
      alert('cart has already cleared');
    }
    
  };

  const handleProceedToCheckOut = ()=>{
    if(totalAmount>0){
      navigate('/order')
    }
    else{
      alert('Add Items in the cart');
    }
  }

  return (
    <div style={{ maxWidth: '800px', margin: '0 auto', padding: '20px' }}>
      <h1 class="text-center text-2xl font-bold">My Cart</h1>

      <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px' }}>
        <tbody>
          {Array.isArray(cartItems) && cartItems.map((item) => (
            <tr key={item.itemId} style={{ borderBottom: '1px solid #e0e0e0' }}>
              <td style={{ padding: '10px' }}>
                <img src={item.image} alt={item.itemName} style={{ width: '90px', height: '70px' }} />
              </td>
              <td style={{ padding: '10px' }}>
                <h3>{item.itemName}</h3>
                <p>₹ {item.price}</p>
              </td>
              <td style={{ padding: '10px' }}>
                <button onClick={() => handleDecreaseQuantity(item.itemId)}>-</button>
                <span style={{ margin: '0 10px' }}>{item.quantity}</span>
                <button onClick={() => handleIncreaseQuantity(item.itemId)}>+</button>
              </td>
              <td style={{ padding: '10px' }}>
                <button
                  type="button"
                  className="px-3 py-2 text-sm mt-2 text-white bg-gradient-to-r from-red-400 via-red-500 to-red-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2"
                  onClick={() => handleRemoveItem(item.itemId)}
                >
                  Remove
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div style={{ textAlign: 'right', marginTop: '20px', fontSize: '1.2em' }}>
        <strong>Total Cart Amount: ₹{totalAmount}</strong>
      </div>

      <div style={{ display: 'flex', justifyContent: 'space-between', marginTop: '20px' }}>
      <button type="button" class="text-white bg-gradient-to-r from-red-400 via-red-500 to-red-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 shadow-lg shadow-red-500/50 dark:shadow-lg 
      dark:shadow-red-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2"
      onClick={handleClearCart}>Clear Cart</button>

        <button type="button" className="text-white bg-gradient-to-r from-green-400 via-green-500 to-green-600 
        hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-green-300 dark:focus:ring-green-800 shadow-lg 
        shadow-green-500/50 dark:shadow-lg dark:shadow-green-800/80 font-medium rounded-lg text-sm px-5 py-2.5 
        text-center me-2 mb-2"
        onClick={handleProceedToCheckOut}
        >Check Out</button>
      </div>
    </div>
  );
};

export default Cart;
