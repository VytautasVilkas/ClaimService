package ClaimService.ClaimService.ClaimService.Validations;

import ClaimService.ClaimService.ClaimService.Models.User;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@NoArgsConstructor
public class ClaimValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"damage","NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"productId","NotEmpty");
        // Add more validation rules as per your requirements
    }
}