package ClaimService.ClaimService.ClaimService.Exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public  UserNotFoundException(UUID id){
        super("Could not found the user with id "+ id);
    }
}
