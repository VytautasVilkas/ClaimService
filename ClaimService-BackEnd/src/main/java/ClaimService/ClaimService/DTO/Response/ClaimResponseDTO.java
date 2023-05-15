package ClaimService.ClaimService.DTO.Response;

import ClaimService.ClaimService.ClaimService.Models.ImageData;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Data
    @RequiredArgsConstructor
    public class ClaimResponseDTO {
        private Long id;
        private String message;
        private LocalDateTime date;
        private double damage;
        private Long photoId;
        private String productName;
        private String userName;
    }

