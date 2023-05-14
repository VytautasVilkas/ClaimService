package ClaimService.ClaimService.DTO.Request;


import jakarta.validation.constraints.DecimalMin;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@Data
@RequiredArgsConstructor
@Validated
public class ClaimRequestDTO {
        @Size(min = 10, message = "message should have at least 10 characters")
        private String message;
        @DecimalMin(value = "0.0", inclusive = false, message = "damage must be greater than 0")
        private double damage;
        private Long productId;
        private Long userId;
        private Long imageId;


}
