import React, { useState, useContext } from 'react';
import { UserContext } from '../UserContext';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useTranslation } from 'react-i18next';

function Login() {
  const { t } = useTranslation();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { setIsLoggedIn, setUserId } = useContext(UserContext); 
  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:9000/users/login', { username, password });
      const { isValidUser, user } = response.data;
      if (isValidUser) {
        console.log(user.id);
        console.log(user.username);
        console.log(user.email);
        localStorage.setItem('userId', user.id);
        localStorage.setItem('username', user.username);
        setUserId(user.id); 
        navigate('/home', { state: { userId: user.id } });
      } else {
        setError('Invalid username or password');
      }
    } catch (error) {
      setError('An error occurred. Please try again.');
    }
  };
  const handleRegistrationClick = () => {
    navigate('/registration');
  };


  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Login</h2>
          {error && <div className="alert alert-danger">{error}</div>}
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">
              {t('username')}
              </label>
              <input
                type="text"
                className="form-control"
                id="username"
                placeholder={t('enter Username')}
                value={username}
                onChange={handleUsernameChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
              {t('password')}
              </label>
              <input
                type="password"
                className="form-control"
                id="password"
                placeholder={t('password')}
                value={password}
                onChange={handlePasswordChange}
              />
            </div>
            <div className="text-center">
              <button type="submit" className="btn btn-primary">
                Login
              </button>
              <button type="button" className="btn btn-primary" onClick={handleRegistrationClick}>
                Register
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;