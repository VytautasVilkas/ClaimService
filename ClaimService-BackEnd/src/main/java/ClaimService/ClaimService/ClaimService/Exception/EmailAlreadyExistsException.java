package ClaimService.ClaimService.ClaimService.Exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String emailAlreadyExists){
        super("user with email "+ emailAlreadyExists +" already exist");
    }
}
