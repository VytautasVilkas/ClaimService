import React, { useState } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import Addclaim from './users/Addclaim';
import EditClaim from './users/EditClaim';
import Login from './pages/Login';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  return (
    <div className="App">
      <Router>
        <Navbar isLoggedIn={isLoggedIn} onLogout={handleLogout} />
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="/addclaim" element={<Addclaim />} />
          <Route path="/editclaim/:id" element={<EditClaim />} />
          <Route path="/" element={<Login onLogin={handleLogin} />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;

