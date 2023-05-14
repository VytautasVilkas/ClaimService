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
  const deleteClaim = async (id) => {
    await axios.delete(`http://localhost:9000/client/deleteclaim/${id}`);
    loadClaims();
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
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {claims.map((claim, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{claim.message}</td>
                <td>{Number(claim.damage)}</td>
                <td>{claim.productName}</td>
                <td>
                  {claim.photoId ? (
                    <PictureComponent photoId={claim.photoId} />
                  ) : (
                    <span>No Photo Available</span>
                  )}
                </td>
                <td>
                  <Link className="btn btn-outline-primary mx-2"
                  to={`/editclaim/${claim.id}`}>
                  edit</Link>
                  <button className="btn btn-danger mx-2"
                  onClick={() => deleteClaim(claim.id)}>
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
