package ClaimService.ClaimService.Security.AuthController;

import ClaimService.ClaimService.Security.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String Username;
    private String Password;
    private String Name;
    private String Surname;
    private String Email;
    private Role role;

}
