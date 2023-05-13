package ClaimService.ClaimService.DTO.Request;

import ClaimService.ClaimService.ClaimService.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRequestDTO {
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String username;
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 2, message = "surname should have at least 2 characters")
    private String surname;
    private Role role;


}
