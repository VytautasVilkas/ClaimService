package ClaimService.ClaimService.ClaimService.Validations;

import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Service.UserService;
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
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"surname","NotEmpty");
        // Add more validation rules as per your requirements
    }
}

