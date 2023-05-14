package ClaimService.ClaimService.ClaimService.Exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(Long id) {
        super("Could not found the image " + id);
    }
}


