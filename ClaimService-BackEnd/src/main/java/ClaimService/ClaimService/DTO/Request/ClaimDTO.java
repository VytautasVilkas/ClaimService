package ClaimService.ClaimService.DTO;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class ClaimDTO {

        private String message;
        private double damage;
        private LocalDate date;
        private byte[] photoData;
        private Long productId;
        private Long userId;

}
