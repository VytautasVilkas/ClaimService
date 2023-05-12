package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Exception.ClaimNotFoundException;
import ClaimService.ClaimService.ClaimService.Exception.ProductNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.ClaimRepository;
import ClaimService.ClaimService.ClaimService.Repositories.ProductRepository;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Response.ClaimResponseDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClaimService {
    private ClaimRepository claimRepository;
    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }
    private ProductRepository productRepository;
    private UserRepository userRepository;
    public void addclaim(ClaimRequestDTO claimRequestDTO) {
        Claim claim = new Claim();
        claim.setMessage(claimRequestDTO.getMessage());
        claim.setDamage(claimRequestDTO.getDamage());
        claim.setPhotoData(claimRequestDTO.getPhotoData());
        Optional<Product> productOptional = productRepository.findById(claimRequestDTO.getProductId());
        productOptional.ifPresent(claim::setProduct);
        claimRepository.save(claim);
    }

    public void updateClaim(Long claimId, ClaimRequestDTO claimRequestDTO) {
        Optional<Claim> claimOptional = claimRepository.findById(claimId);
        if (claimOptional.isPresent()) {
            Claim claim = claimOptional.get();
            claim.setMessage(claimRequestDTO.getMessage());
            claim.setDamage(claimRequestDTO.getDamage());
            claim.setPhotoData(claimRequestDTO.getPhotoData());
            Optional<Product> productOptional = productRepository.findById(claimRequestDTO.getProductId());
            productOptional.ifPresent(claim::setProduct);

            claimRepository.save(claim);
        } else {
            throw new ClaimNotFoundException(claimId);
        }
    }

    public void deleteClaim(Long claimId) {
        Optional<Claim> claimOptional = claimRepository.findById(claimId);
        if (claimOptional.isPresent()) {
            Claim claim = claimOptional.get();
            claimRepository.delete(claim);
        } else {
            throw new ClaimNotFoundException(claimId);
        }
    }
    public ClaimResponseDTO findClaimById(Long claimId) {
        Optional<Claim> claimOptional = claimRepository.findById(claimId);
        if (claimOptional.isPresent()) {
            Claim claim = claimOptional.get();
            return convertToClaimResponseDTO(claim);
        } else {
            throw new ClaimNotFoundException(claimId);
        }
    }

    public List<ClaimResponseDTO> findAllClaims() {
        List<Claim> claims = claimRepository.findAll();
        return claims.stream()
                .map(this::convertToClaimResponseDTO)
                .collect(Collectors.toList());
    }

    private ClaimResponseDTO convertToClaimResponseDTO(Claim claim) {
        ClaimResponseDTO dto = new ClaimResponseDTO();
        dto.setMessage(claim.getMessage());
        dto.setDamage(claim.getDamage());
        dto.setPhotoData(claim.getPhotoData());
        Long productId = claim.getProduct().getId();
        if (productId != null) {
            Optional<Product> productOptional = productRepository.findById(productId);
            productOptional.ifPresent(product -> dto.setProductName(product.getProductname()));
        } else throw new ProductNotFoundException(productId);

//        UUID userId = claim.getUser().getId();
//        if (userId != null) {
//            Optional<User> userOptional = userRepository.findById(userId);
//            userOptional.ifPresent(user -> dto.setUsername(user.getUsername()));
//        }
        return dto;
    }




}



