package ClaimService.ClaimService.ClaimService.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;


import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productname;
    @Min(value = 0, message = "price should be more then 0")
    private double price;
    @OneToMany(mappedBy = "product")
    private List <Claim> claims;
}
