package ClaimService.ClaimService;

import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Service.ProductService;
import ClaimService.ClaimService.DTO.ProductDTO;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class RandomProductGenerator {
    private final ProductService productService;

    public RandomProductGenerator(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void generateRandomProducts() {
//        Faker faker = new Faker();

//        IntStream.range(0, 20).forEach(i -> {
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setProductname(faker.commerce().productName());
//            productDTO.setPrice(faker.number().randomDouble(2, 0, 1000));
//
//            productService.addProduct(productDTO);
//        });
    }
}
