package ClaimService.ClaimService.DTO.Response;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import ClaimService.ClaimService.ClaimService.Models.Claim;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UserResponseDTO {
        private Long id;
        private String username;
        private String name;
        private String surname;
        private String email;
        private Role role;
        boolean isValid=true;
//        private List<Claim> claims;


}
