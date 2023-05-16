import React from 'react';

const NotFound = () => {
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 text-center">
          <h1>404 - Page Not Found</h1>
          <p>Oops! The page you are looking for does not exist.</p>
          <span role="img" aria-label="Sad Face" style={{ fontSize: '4rem' }}>
            😢
          </span>
        </div>
      </div>
    </div>
  );
};

export default NotFound;
