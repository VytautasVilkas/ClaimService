package ClaimService.ClaimService.DTO.Response;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UserResponseDTO {
        private String username;
        private String email;
        private Role role;


}
