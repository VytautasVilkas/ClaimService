package ClaimService.ClaimService.DTO.Request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@RequiredArgsConstructor
@Validated
public class UserLoginRequest {
    private String username;
    private String Password;

}
