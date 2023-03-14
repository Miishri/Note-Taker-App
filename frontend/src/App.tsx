import React from 'react';
import { Navbar } from './components/Navigation/Navbar';
import { Home } from './components/Pages/Home';
function App(): any {
  return (
    <div className="flex flex-col h-screen">
      <Navbar />
      <Home />
    </div>
  );
}

export default App;
