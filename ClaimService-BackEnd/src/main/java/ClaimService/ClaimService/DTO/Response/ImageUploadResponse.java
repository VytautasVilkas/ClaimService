package ClaimService.ClaimService.DTO.Response;


import ClaimService.ClaimService.ClaimService.Models.ImageData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUploadResponse {
    private Long id;
    private String message;
    private String imageName;

}

