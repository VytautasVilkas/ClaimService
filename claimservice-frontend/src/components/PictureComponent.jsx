import React, { useEffect, useState } from 'react';
import axios from 'axios';

function PictureComponent({ photoId }) {
  const [pictureData, setPictureData] = useState(null);

  useEffect(() => {
    loadPictureData();
  }, [photoId]);

  const loadPictureData = async () => {
    try {
      const response = await axios.get(`http://localhost:9000/image/${photoId}`);
      setPictureData(response.data);
    } catch (error) {
      console.error('Error fetching picture data:', error);
      setPictureData(null);
    }
  };

  return pictureData ? (
    <img src={pictureData.url} alt="Claim" />
  ) : (
    <span>No Photo Available</span>
  );
}

export default PictureComponent;
