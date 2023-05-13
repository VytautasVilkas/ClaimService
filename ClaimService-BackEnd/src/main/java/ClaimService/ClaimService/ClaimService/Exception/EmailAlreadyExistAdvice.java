package ClaimService.ClaimService.ClaimService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmailAlreadyExistAdvice {
    @ResponseBody
    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> exceptionHandler(EmailAlreadyExistsException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return errorMap;
    }
}
