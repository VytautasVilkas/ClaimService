package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Service.ProductService;
import ClaimService.ClaimService.DTO.ProductDTO;
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

    public ProductController(ProductService productService) {
            this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDTO) {
            productService.addProduct(productDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = productService.findProductById(id);
        if (productDTO != null) {
            ProductDTO responseDTO = new ProductDTO();
            responseDTO.setId(productDTO.getId());
            responseDTO.setProductname(productDTO.getProductname());
            responseDTO.setPrice(productDTO.getPrice());

            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        List<ProductDTO> productDTOs = productService.findAllProducts();
        List<ProductDTO> responseDTOs = new ArrayList<>();

        for (ProductDTO productDTO : productDTOs) {
            ProductDTO responseDTO = new ProductDTO();
            responseDTO.setId(productDTO.getId());
            responseDTO.setProductname(productDTO.getProductname());
            responseDTO.setPrice(productDTO.getPrice());
            responseDTOs.add(responseDTO);
        }

        return ResponseEntity.ok(responseDTOs);
    }
}
