package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Service.ProductService;
import ClaimService.ClaimService.DTO.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/addproduct")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO addedProduct = productService.addProduct(productDTO);
        return ResponseEntity.ok(addedProduct);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long productId) {
        ProductDTO productDTO = productService.findProductById(productId);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.findAllProducts();
        return ResponseEntity.ok(productDTOs);
    }
}
