package ClaimService.ClaimService.ClaimService.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private double damage;
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Lob
    private byte[] photoData;


}
