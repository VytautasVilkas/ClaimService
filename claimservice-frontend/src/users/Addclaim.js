import React, { useEffect,useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import ImageUploader from '../components/ImageUploader';



export default function AddClaim() {
  const navigate = useNavigate();

  const [claim, setClaim] = useState({
    message: '',
    damage: 0.0,
    productId: null,
    userId: '',
    imageId: '',
  });
  const [claimErrors, setClaimErrors] = useState({});
  const { message, damage, productId, userId, imageId } = claim;
  const [productList, setProductList] = useState([]);
    
  useEffect(() => {
    const fetchProductList = async () => {
      try {
        const response = await axios.get('http://localhost:9000/products/getall');
        const products = Array.isArray(response.data)
          ? response.data.map((product) => ({
              id: product.id,
              productName: product.productName,
              price: product.price,
            }))
          : [];
        setProductList(products);
      } catch (error) {
        console.error('Error fetching product list:', error);
        setProductList([]);
      }
    };

    fetchProductList();
  }, []);

  const onInputChange = (e) => {
    setClaim({ ...claim, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    
    if (!productId) {
      setClaimErrors({ ...claimErrors, productId: 'Please select a product' });
      return;
    }
    if (!imageId) {
      setClaimErrors({ ...claimErrors, imageId: 'Please upload an image' });
      return;
    }
    try {
      const response = await axios.post('http://localhost:9000/client/addclaim', claim);
      const { id } = response.data;
      console.log('Claim added with ID:', id);
      navigate('/');
    } catch (error) {
      if (error.response && error.response.data) {
        const { data } = error.response;
        const validationErrors = Object.entries(data).reduce(
          (errors, [field, message]) => ({
            ...errors,
            [field]: message,
          }),
          {}
        );
        setClaimErrors(validationErrors);
        console.log(claimErrors);
      }
      console.error('Error adding claim:', error);
    }
  };
  const handleImageUpload = async (selectedImage) => {
    if (!selectedImage) {
      setClaimErrors({ ...claimErrors, imageId: 'Please upload an image' });
      setClaim({ ...claim, imageId: '' });
      return;
    }
  
    const formData = new FormData();
    formData.append('image', selectedImage);
  
    try {
      const response = await axios.post('http://localhost:9000/image/add', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
  
      const { id: imageId } = response.data;
      setClaim({ ...claim, imageId });
      setClaimErrors({ ...claimErrors, imageId: '' }); // Reset the imageId error in the claimErrors state
    } catch (error) {
      console.error('Error uploading image:', error);
    }
  };
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Add Claim Form</h2>
          <form onSubmit={onSubmit}>
            <div className="mb-3">
              <label htmlFor="message" className="form-label">
                Message
              </label>
              <input
                type="text"
                className={`form-control ${claimErrors.message && 'is-invalid'}`}
                id="message"
                placeholder="Enter message"
                name="message"
                value={message}
                onChange={onInputChange}
              />
              {claimErrors.message && (
                <div className="invalid-feedback">{claimErrors.message}</div>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="damage" className="form-label">
                Damage
              </label>
              <input
                type="number"
                className={`form-control ${claimErrors.damage && 'is-invalid'}`}
                id="damage"
                placeholder="Enter damage"
                name="damage"
                value={damage}
                onChange={onInputChange}
              />
              {claimErrors.damage && (
                <div className="invalid-feedback">{claimErrors.damage}</div>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="productId" className="form-label">
                Product
              </label>
              <select
                className={`form-select ${claimErrors.productId && 'is-invalid'}`}
                id="productId"
                name="productId"
                value={productId}
                onChange={onInputChange}
              >
                <option value="">Select product</option>
                {productList.map((product) => (
                  <option key={product.id} value={product.id}>
                    {product.productName}
                  </option>
                ))}
              </select>
              {claimErrors.productId && (
                <div className="invalid-feedback d-block">{claimErrors.productId}</div>
              )}
            </div>
            <div className="mb-3">
              <label htmlFor="userId" className="form-label">
                User ID
              </label>
              <input
                type="number"
                className="form-control"
                id="userId"
                placeholder="Enter user ID"
                name="userId"
                value={userId}
                onChange={onInputChange}
              />
              </div>
                <div className="mb-2">
                <label htmlFor="image" className="form-label">
                Upload Image
                </label>
                <div>
                <ImageUploader onImageUpload={(id) => setClaim({ ...claim, imageId: id })}/>
                {claimErrors.imageId && (
                <div className="invalid-feedback">{claimErrors.imageId}</div>
                )}
                </div>
                </div>
                <div className="text-center">
                <button type="submit" className="btn btn-primary">
                Submit
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
