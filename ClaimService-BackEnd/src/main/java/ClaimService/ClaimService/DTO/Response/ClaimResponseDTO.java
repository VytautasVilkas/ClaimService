package ClaimService.ClaimService.DTO.Response;

import ClaimService.ClaimService.ClaimService.Models.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
    @Data
    @RequiredArgsConstructor
    public class ClaimResponseDTO {
        private String message;
        private double damage;
        private byte[] photoData;
        private String productName;
        private String userName;
    }

