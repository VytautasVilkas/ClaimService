package ClaimService.ClaimService.DTO.Request;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class UserUpdateDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Role role;


}
