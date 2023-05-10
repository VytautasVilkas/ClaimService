package ClaimService.ClaimService.ClaimService.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private double damage;
    private LocalDate date = LocalDate.now();
    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products;
    @Lob
    private byte[] photoData;


}
