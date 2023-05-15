import React, { useState } from 'react';
import axios from 'axios';

const ImageUploader = ({ onImageUpload }) => {
  const [selectedImage, setSelectedImage] = useState(null);

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    setSelectedImage(file);
    uploadImage(file);
  };

  const uploadImage = async (image) => {
    const formData = new FormData();
    formData.append('image', image);

    try {
      const response = await axios.post('http://localhost:9000/image/add', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      const { id } = response.data;
      onImageUpload(id); // Trigger the callback with the image ID
      console.log('Image uploaded with ID:', id);
    } catch (error) {
      console.error('Error uploading image:', error);
    }
  };

  return (
    <div>
      <label htmlFor="image">Upload Image:</label>
      <input type="file" id="image" onChange={handleImageChange} />
    </div>
  );
};

export default ImageUploader;



