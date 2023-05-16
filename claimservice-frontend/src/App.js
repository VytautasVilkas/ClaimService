import React, { useState } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import Addclaim from './users/Addclaim';
import EditClaim from './users/EditClaim';
import Login from './pages/Login';
import Registration from './pages/Registration';
import NotFound from './pages/NotFound';
import { UserProvider } from './UserContext';
import { I18nextProvider } from 'react-i18next';
import i18n from './i18n';


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
        <UserProvider> {/* Add UserProvider here */}
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/addclaim" element={<Addclaim />} />
            <Route path="/editclaim/:id" element={<EditClaim />} />
            <Route path="/registration" element={<Registration />} />
            <Route path="/" element={<Login onLogin={handleLogin} />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </UserProvider> {/* Close UserProvider here */}
      </Router>
    </div>
 
);
}

export default App;



