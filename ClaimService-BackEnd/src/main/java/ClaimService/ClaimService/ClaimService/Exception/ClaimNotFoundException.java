package ClaimService.ClaimService.ClaimService.Exception;


public class ClaimNotFoundException extends RuntimeException{
    public ClaimNotFoundException(Long id){
        super("Could not found the claim with id "+ id);
    }
}
