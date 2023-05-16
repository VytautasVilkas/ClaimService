import React, { useState } from 'react';
import { Route, BrowserRouter as Router, Routes, Outlet } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import Addclaim from './users/Addclaim';
import EditClaim from './users/EditClaim';
import Login from './pages/Login';
import Registration from './pages/Registration';
import NotFound from './pages/NotFound';
import { UserProvider, UserContext } from './UserContext';
import PrivateRoutes from './PrivateRoutes';
import { I18nextProvider } from 'react-i18next';
import i18n from './i18n';




function App() {
  return (
  <div className="App">
   <UserProvider>
    <Router>
      <Navbar />
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="/addclaim" element={<Addclaim />} />
          <Route path="/editclaim/:id" element={<EditClaim />} />
          <Route path="/registration" element={<Registration />} />
          <Route path="/" element={<Login/>} />
          <Route path="*" element={<NotFound />} />
        </Routes>
    </Router>
   </UserProvider>
  </div>
  );
}

export default App;
