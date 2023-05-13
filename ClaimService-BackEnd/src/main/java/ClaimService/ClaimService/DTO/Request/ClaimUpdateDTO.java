package ClaimService.ClaimService.DTO.Request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@RequiredArgsConstructor
@Validated
public class ClaimUpdateDTO {
    @NotEmpty
    @Size(min = 10, message = "message should have at least 10 characters")
    private String message;
    private double damage;
    private Long productId;
}
