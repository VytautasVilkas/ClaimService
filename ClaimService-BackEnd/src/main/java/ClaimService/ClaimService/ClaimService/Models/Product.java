package ClaimService.ClaimService.ClaimService.Models;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productname;
    private double price;
    @OneToMany(mappedBy = "product")
    private List <Claim> claims;
}
