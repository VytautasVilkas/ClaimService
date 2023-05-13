package ClaimService.ClaimService.DTO.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserUpdateDTO {
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String username;
    @NotEmpty
    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 2, message = "name should have at least 2 characters")
    private String surname;
    @NotEmpty
    @Email
    private String email;
}
