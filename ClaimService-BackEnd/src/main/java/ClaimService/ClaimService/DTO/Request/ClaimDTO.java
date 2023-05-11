package ClaimService.ClaimService.DTO.Request;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ClaimDTO {

        private String message;
        private double damage;
        private byte[] photoData;
        private Long productId;
        private long userId;

}
