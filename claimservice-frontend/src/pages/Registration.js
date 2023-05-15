import React, { useState } from 'react';
import axios from 'axios';
import { Link ,useNavigate} from 'react-router-dom';

export default function RegistrationForm() {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    name: '',
    surname: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });
  

  const [errors, setErrors] = useState({});

  const { name,surname,username, email, password, confirmPassword } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setErrors({ confirmPassword: 'Passwords do not match' });
      return;
    }

    try {
      const response = await axios.post('http://localhost:9000/users/create', user);
      console.log('User registered:', response.data);
      navigate('/');
    } catch (error) {
      if (error.response && error.response.data) {
        const { data } = error.response;
        setErrors(data);
      }
      console.error(errors.errorMessage, error);
    }
  };
  
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Registration Form</h2>
          <form onSubmit={onSubmit}>
          {errors.errorMessage && <div className="alert alert-danger">{errors.errorMessage}</div>}

            <div className="mb-3">
              <label htmlFor="username" className="form-label">
                Username
              </label>
              <input
                type="text"
                className={`form-control ${errors.username && errors.errorMessage && 'is-invalid'}`}
                id="username"
                placeholder="Enter username"
                name="username"
                value={username}
                onChange={onInputChange}
              />
              {errors.username && <div className="invalid-feedback">{errors.username}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name
              </label>
              <input
                type="text"
                className={`form-control ${errors.name && 'is-invalid'}`}
                id="name"
                placeholder="Enter name"
                name="name"
                value={name}
                onChange={onInputChange}
              />
              {errors.name && <div className="invalid-feedback">{errors.name}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="surname" className="form-label">
                Surname
              </label>
              <input
                type="text"
                className={`form-control ${errors.surname && 'is-invalid'}`}
                id="surname"
                placeholder="Enter surname"
                name="surname"
                value={surname}
                onChange={onInputChange}
              />
              {errors.surname && <div className="invalid-feedback">{errors.surname}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                type="email"
                className={`form-control ${errors.email && 'is-invalid'}`}
                id="email"
                placeholder="Enter email"
                name="email"
                value={email}
                onChange={onInputChange}
              />
              {errors.email && <div className="invalid-feedback">{errors.email}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password
              </label>
              <input
                type="password"
                className={`form-control ${errors.password && 'is-invalid'}`}
                id="password"
                placeholder="Enter password"
                name="password"
                value={password}
                onChange={onInputChange}
              />
              {errors.password && <div className="invalid-feedback">{errors.password}</div>}
            </div>
            <div className="mb-3">
              <label htmlFor="confirmPassword" className="form-label">
                Confirm Password 
              </label>
              <input
                type="password"
                className={`form-control ${errors.confirmPassword && 'is-invalid'}`}
                id="confirmPassword"
                placeholder="Confirm password"
                name="confirmPassword"
                value={confirmPassword}
                onChange={onInputChange}
              />
              {errors.confirmPassword && (
                <div className="invalid-feedback">{errors.confirmPassword}</div>
              )}
            </div>
            <div className="text-center">
              <button type="submit" className="btn btn-primary">
                Register
              </button>
              <Link className="btn btn-danger mx-2" to="/">
                Cancel
                </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

