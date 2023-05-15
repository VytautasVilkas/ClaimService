import React, { useEffect, useState } from 'react';
import axios from 'axios';
import PictureComponent from '../components/PictureComponent';
import { Link } from 'react-router-dom';




export default function Home() {
  const [claims, setClaims] = useState([]);

  useEffect(() => {
    loadClaims();
  }, []);
  
   
  const loadClaims = async () => {
    try {
      const result = await axios.get('http://localhost:9000/client/allclaims');
      setClaims(result.data);
    } catch (error) {
      console.error('Error fetching claims:', error);
    }
  };
  const deleteClaim = async (id, photoId) => {
    try {
      // Delete the claim
      await axios.delete(`http://localhost:9000/client/deleteclaim/${id}`);

      // Delete the associated image
      if (photoId) {
        await axios.delete(`http://localhost:9000/image/delete/${photoId}`);
      }

      // Reload the claims
      await loadClaims();
    } catch (error) {
      console.error('Error deleting claim:', error);
    }
  };

  const formatCurrency = (amount) => {
    return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(amount);
  };
  const formatDate = (dateString) => {
    if (!dateString) {
      return ''; // or return a default value if needed
    }
  
    const [datePart, timePart] = dateString.split('T');
    const [year, month, day] = datePart.split('-');
    const [hour, minute, second] = timePart.split(':');
    const formattedDate = new Date(year, month - 1, day, hour, minute, second);
    return formattedDate.toLocaleDateString();
  };

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Message</th>
              <th scope="col">Damage</th>
              <th scope="col">Product Name</th>
              <th scope="col">Photo</th>
              <th scope="col">Data</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {claims.map((claim, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{claim.message}</td>
                <td>{formatCurrency(claim.damage)}</td>
                <td>{claim.productName}</td>
                <td>
                  {claim.photoId ? (
                    <PictureComponent photoId={claim.photoId} />
                  ) : (
                    <span>No Photo Available</span>
                  )}
                </td>
                <td>{formatDate(claim.date)}</td>

                <td>
                  <Link className="btn btn-outline-primary mx-2"
                  to={`/editclaim/${claim.id}`}>
                  edit</Link>
                  <button className="btn btn-danger mx-2"
                  onClick={() => deleteClaim(claim.id,claim.photoId)}>
                  delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="text-center">
          <Link className="btn btn-primary" to="/addclaim">Add Claim</Link>
        </div>
      </div>
    </div>
  );
}
