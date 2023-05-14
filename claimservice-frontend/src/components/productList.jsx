import axios from 'axios';

const fetchProductList = async () => {
  try {
    const response = await axios.get('http://localhost:9000/products/getall');
    return response.data.map((product) => ({
      id: product.id,
      productName: product.productName,
      price: product.price,
    }));
  } catch (error) {
    console.error('Error fetching product list:', error);
    return [];
  }
};

const productList = (async () => {
  return await fetchProductList();
})();

export default productList;
