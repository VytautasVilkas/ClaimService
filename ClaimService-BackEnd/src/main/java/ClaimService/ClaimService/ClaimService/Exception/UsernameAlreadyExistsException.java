package ClaimService.ClaimService.ClaimService.Exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String usernameAlreadyExists) {
        super("user with username "+ usernameAlreadyExists+" already exist");
    }
}
