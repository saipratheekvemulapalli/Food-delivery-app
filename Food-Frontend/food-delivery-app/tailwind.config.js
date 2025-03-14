/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
    "./node_modules/flowbite/**/*.js" // Add this line
  ],
  theme: {
    extend: {
      colors: {
        'custom-clr': '#3C6E71',
        'RebeccaPurple':'#663399',
        'Indigo':'#4B0082',
        'SeaGreen':'#2E8B57',
        'DarkGreen':'#006400'
        
      },
    },
  },
  plugins: [
    require('flowbite/plugin') // Add this line
  ],
};

