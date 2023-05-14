package ClaimService.ClaimService.DTO.Response;

import ClaimService.ClaimService.ClaimService.Models.ImageData;

import lombok.Data;
import lombok.RequiredArgsConstructor;




@Data
    @RequiredArgsConstructor
    public class ClaimResponseDTO {
        private Long id;
        private String message;
        private double damage;
        private Long photoId;
        private String productName;
        private String userName;
    }

