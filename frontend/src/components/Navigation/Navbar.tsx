import React, { useState } from 'react';

export const Navbar = (): JSX.Element => {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  return (
    <nav className="bg-white shadow-sm">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          <div className="flex-shrink-0">
            <a href="/" className="flex items-center">
              <svg
                className="h-6 w-6 text-green-600 mr-2"
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
              <span className="font-medium text-green-600 text-lg">Name Pending</span>
            </a>
          </div>
          <div className="hidden lg:flex space-x-4 lg:items-center lg:justify-end lg:flex-1">
            <a
              href="/notes"
              className="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:text-green-600 hover:bg-green-100 focus:outline-none focus:text-green-600 focus:bg-green-100">
              Notes
            </a>
            <a
              href="/about"
              className="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:text-green-600 hover:bg-green-100 focus:outline-none focus:text-green-600 focus:bg-green-100">
              About
            </a>
            <a
              href="/contact"
              className="px-3 py-2 rounded-md text-sm font-medium text-gray-700 hover:text-green-600 hover:bg-green-100 focus:outline-none focus:text-green-600 focus:bg-green-100">
              Contact
            </a>
            <button className=" px-3 py-2 bg-green-600 hover:bg-green-700 rounded-md text-white">
              Get Started
            </button>
          </div>
          <div className="flex lg:hidden">
            <button
              type="button"
              className="inline-flex items-center justify-center p-2 rounded-md text-green-600 hover:text-green-700 hover:bg-green-100 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-100 focus:ring-green-600"
              aria-expanded={isMobileMenuOpen}
              aria-haspopup="true"
              onClick={() => {
                setIsMobileMenuOpen(!isMobileMenuOpen);
              }}>
              <span className="sr-only">Open main menu</span>
              <svg
                className={`${isMobileMenuOpen ? 'hidden' : 'block'} h-6 w-6`}
                viewBox="0 0 24 24"
                fill="none"
                xmlns="http://www.w3.org/2000/svg">
                <path
                  d="M4 6H20M4 12H20M4 18H20"
                  stroke="currentColor"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
              </svg>
              <svg
                className={`${isMobileMenuOpen ? 'block' : 'hidden'} h-6 w-6`}
                viewBox="0 0 24 24"
                fill="none"
                xmlns="http://www.w3.org/2000/svg">
                <path
                  d="M6 18L18 6M6 6L18 18"
                  stroke="currentColor"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                />
              </svg>
            </button>
          </div>
        </div>
      </div>

      {/* Mobile menu, toggle className based on menu state */}
      <div className={`${isMobileMenuOpen ? 'block' : 'hidden'} lg:hidden`}>
        <div className="px-2 pt-2 pb-3 space-y-2">
          <a
            href="/notes"
            className="block px-3 py-2 rounded-md text-base font-medium text-gray-700 hover:text-green-600 hover:bg-green-100 focus:outline-none focus:text-green-600 focus:bg-green-100">
            Notes
          </a>
          <a
            href="/about"
            className="block px-3 py-2 rounded-md text-base font-medium text-gray-700 hover:text-green-600 hover:bg-green-100 focus:outline-none focus:text-green-600 focus:bg-green-100">
            About
          </a>
          <a
            href="/contact"
            className="block px-3 py-2 rounded-md text-base font-medium text-gray-700 hover:text-green-600 hover:bg-green-100 focus:outline-none focus:text-green-600 focus:bg-green-100">
            Contact
          </a>
          <button className=" px-3 py-2 w-full hover:bg-green-700 bg-green-600 rounded-md text-white">
            Get Started
          </button>
        </div>
      </div>
    </nav>
  );
};
