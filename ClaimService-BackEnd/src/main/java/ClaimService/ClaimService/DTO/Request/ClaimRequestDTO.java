package ClaimService.ClaimService.DTO.Request;

import lombok.*;

@Data
@RequiredArgsConstructor
public class ClaimRequestDTO {
        private String message;
        private double damage;
        private byte[] photoData;
        private Long productId;
        private long userId;


}
