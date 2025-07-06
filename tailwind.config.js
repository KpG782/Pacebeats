/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class', // enable class-based dark mode
  content: [
    "./App.{js,jsx,ts,tsx}",
    "./app/**/*.{js,jsx,ts,tsx}",
    "./components/**/*.{js,jsx,ts,tsx}",
    "./screens/**/*.{js,jsx,ts,tsx}",
  ],
  presets: [require("nativewind/preset")],
  theme: {
    extend: {
      colors: {
        // Background primary: light defaults to white, dark overrides to #1E1E1E
        primary: {
          DEFAULT: '#FFFFFF',
          dark: '#1E1E1E',
        },
        secondary: '#3F4ED3',
        light: {
          100: '#D6C6FF',
          200: '#A8B5DB',
          300: '#9CA4AB',
        },
        dark: {
          100: '#221f3d',
          200: '#0f0d23',
        },
        accent: '#AB8BFF',
        // Primary font color: light mode black, dark mode white
        'font-primary': {
          DEFAULT: '#000000',
          dark: '#FFFFFF',
        },
      },
      fontFamily: {
        primary: ["Roboto", "sans-serif"],
        poppins: ['Poppins_400Regular'],   // default weight
        'poppins-bold': ['Poppins_700Bold']
      },
    },
  },
  plugins: [],
}
