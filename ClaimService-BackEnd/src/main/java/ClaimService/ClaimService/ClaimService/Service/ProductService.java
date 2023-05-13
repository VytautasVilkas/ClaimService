package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Exception.ProductNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Repositories.ProductRepository;
import ClaimService.ClaimService.DTO.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
        private final ProductRepository productRepository;
        private final ModelMapper modelMapper;

        public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
            this.productRepository = productRepository;
            this.modelMapper = modelMapper;
        }

        public ProductDTO addProduct(ProductDTO productDTO) {
            Product product = modelMapper.map(productDTO, Product.class);
            Product savedProduct = productRepository.save(product);
            return modelMapper.map(savedProduct, ProductDTO.class);
        }

        public ProductDTO findProductById(Long productId) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                return modelMapper.map(product, ProductDTO.class);
            }else {
                throw new ProductNotFoundException(productId);
            }
        }

        public List<ProductDTO> findAllProducts() {
            List<Product> products = productRepository.findAll();
            return products.stream()
                    .map(product -> modelMapper.map(product, ProductDTO.class))
                    .collect(Collectors.toList());
        }
}

