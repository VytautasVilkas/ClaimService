package ClaimService.ClaimService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDTO {
    private long id;
    private String productname;
    private double price;

}