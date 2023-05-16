import React from 'react';
import { useTranslation } from 'react-i18next';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFlag, faLanguage } from '@fortawesome/free-solid-svg-icons';

export default function Navbar() {
  const { t, i18n } = useTranslation();

  const changeLanguage = (lng) => {
    i18n.changeLanguage(lng);
  };

  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            Claim Service
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <button className="btn btn-link nav-link" onClick={() => changeLanguage('en')}>
                  <FontAwesomeIcon icon={faFlag} className="mr-1" />
                  English
                </button>
              </li>
              <li className="nav-item">
                <button className="btn btn-link nav-link" onClick={() => changeLanguage('lt')}>
                  <FontAwesomeIcon icon={faFlag} className="mr-1" />
                  Lithuanian
                </button>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
}
