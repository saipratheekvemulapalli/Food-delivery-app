import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Payment = () => {
  const [amount, setAmount] = useState(sessionStorage.getItem('totalCost'));
  const orderId = sessionStorage.getItem('orderId');
  const username = localStorage.getItem('username');
  const RAZORPAY_KEY_ID = "rzp_test_XYjFHozA8gY1CB";
  const navigate = useNavigate();

  useEffect(() => {
    const script = document.createElement("script");
    script.src = "https://checkout.razorpay.com/v1/checkout.js";
    script.async = true;
    document.body.appendChild(script);
    return () => {
      document.body.removeChild(script);
    };
  }, []);

  const handlePayment = async () => {
    const options = {
      key: RAZORPAY_KEY_ID,
      amount: amount * 100, // Amount in paise
      currency: "INR",
      name: username, // Show username
      description: `Payment for Order ID: ${orderId}`,
      handler: async (response) => {
        try {
          const { razorpay_payment_id } = response;
          const paymentDate = new Date().toISOString();
          const transactionStatus = "Success";

          // Save payment details to backend
          await axios.post(
            'http://localhost:8080/registration/authorization/doPayment',
            {
              transactionId: razorpay_payment_id,
              amount,
              orderId,
              paymentDate,
              transactionStatus,
              username,
            },
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
              },
            }
          );

          alert("Payment Successful!");
          navigate('/myorders'); // Redirect to orders page
        } catch (error) {
          console.error("Error while storing payment details:", error);
          alert("Payment was successful, but we couldn't store the details. Please check your orders.");
          navigate('/myorders');
        }
      },
      prefill: {
        name: username,
        email: "user@example.com",
        contact: "1234567890",
      },
      notes: {
        orderId,
      },
      theme: {
        color: "#228B22",
      },
      method: {
        upi: true,      
        card: true,     
        netbanking: true, 
      },
    };

    const razorpay = new window.Razorpay(options);
    razorpay.open();
  };

  return (
    <div className="flex flex-col items-center mt-8">
      <h1 className="text-blue-600 font-bold text-2xl mb-4">Make Payment</h1>
      <button
        onClick={handlePayment}
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
      >
        Pay ₹{amount}
      </button>
    </div>
  );
};

export default Payment;
