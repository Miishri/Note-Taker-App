import React from 'react';

export const Home = (): JSX.Element => {
  return (
    <div className="flex flex-1 flex-col md:flex-row items-center justify-center">
      <div className="animate-fade-in-up md:mr-16">
        <svg
          className="w-32 mb-8 mx-auto text-green-600"
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg">
          <path
            d="M12 1L2 7V17L12 23L22 17V7L12 1Z"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
          <path
            d="M12 22V11"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
          <path
            d="M7 13H17"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
          <path
            d="M7 17H17"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
        </svg>
      </div>
      <div className="animate-fade-in-right">
        <h1 className="text-3xl font-bold mb-4">Name Pending</h1>
        <p className="text-gray-600 mb-8">A simple note-taking app for all your needs</p>
        <button className="bg-green-600 text-white py-2 px-4 rounded-lg hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-600 focus:ring-opacity-50 transition-all ease-in-out duration-300">
          Get started
        </button>
      </div>
    </div>
  );
};
