package ClaimService.ClaimService.ClaimService.Validations;

import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Service.UserService;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@NoArgsConstructor
public class UserValidator implements Validator {
    UserService userService;
    public UserValidator(UserService userService) {
            this.userService = userService;
        }
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequestDTO.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        UserRequestDTO user = (UserRequestDTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty","Username is required.");
        if(user.getUsername().length()<3){
            errors.rejectValue("username", "InvalidLength", "Username must be at least 3 characters long.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty", "Password is required.");
        if (user.getPassword().length() < 6) {
            errors.rejectValue("password", "InvalidLength", "Password must be at least 6 characters long.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty", "Email is required.");
        if (!user.getEmail().matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")) {
            errors.rejectValue("email", "InvalidFormat", "Invalid email format.");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty", "Name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty", "Surname is required.");


    }
}

