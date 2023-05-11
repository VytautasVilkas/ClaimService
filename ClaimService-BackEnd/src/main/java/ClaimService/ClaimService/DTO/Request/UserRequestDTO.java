package ClaimService.ClaimService.DTO.Request;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Role role;
}
