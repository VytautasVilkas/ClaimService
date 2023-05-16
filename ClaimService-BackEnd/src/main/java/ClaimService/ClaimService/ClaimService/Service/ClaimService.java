package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Exception.ClaimNotFoundException;
import ClaimService.ClaimService.ClaimService.Exception.ProductNotFoundException;
import ClaimService.ClaimService.ClaimService.Exception.UserNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.ClaimService.Models.ImageData;
import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.ClaimRepository;
import ClaimService.ClaimService.ClaimService.Repositories.ImageDataRepository;
import ClaimService.ClaimService.ClaimService.Repositories.ProductRepository;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import ClaimService.ClaimService.DTO.Request.ClaimUpdateDTO;
import ClaimService.ClaimService.DTO.Response.ClaimResponseDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ClaimService {


    private final ClaimRepository claimRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ImageDataRepository imageDataRepository;


    public ClaimService(ClaimRepository claimRepository, ModelMapper modelMapper, ProductRepository productRepository, UserRepository userRepository, ImageDataRepository imageDataRepository) {
        this.claimRepository = claimRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.imageDataRepository = imageDataRepository;

    }

    public ClaimResponseDTO addClaim(ClaimRequestDTO claimRequestDTO) {
        Product product = productRepository.findById(claimRequestDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(claimRequestDTO.getProductId()));
        Claim claim = modelMapper.map(claimRequestDTO, Claim.class);
        User user = userRepository.findById(claimRequestDTO.getUserId()).orElse(null);
        claim.setUser(user);
        ImageData image = imageDataRepository.findById(claimRequestDTO.getImageId()).orElse(null);
        claim.setImages(image);
        Claim savedClaim = claimRepository.save(claim);
        return modelMapper.map(savedClaim, ClaimResponseDTO.class);
    }



    public ClaimResponseDTO updateClaim(Long claimId, ClaimUpdateDTO claimUpdateDTO) {
        Optional<Claim> existingClaimOptional = claimRepository.findById(claimId);
        if (existingClaimOptional.isPresent()) {
            Claim existingClaim = existingClaimOptional.get();

            if (claimUpdateDTO.getMessage() != null && !claimUpdateDTO.getMessage().isEmpty()) {
                existingClaim.setMessage(claimUpdateDTO.getMessage());
            }

            if (claimUpdateDTO.getDamage() != 0.0) {
                existingClaim.setDamage(claimUpdateDTO.getDamage());
            }

            if (claimUpdateDTO.getProductId() != null) {
                Product product = productRepository.findById(claimUpdateDTO.getProductId())
                        .orElseThrow(() -> new ProductNotFoundException(claimUpdateDTO.getProductId()));
                existingClaim.setProduct(product);
            }
            Claim updatedClaim = claimRepository.save(existingClaim);
            return modelMapper.map(updatedClaim, ClaimResponseDTO.class);
        } else {
            throw new ClaimNotFoundException(claimId);
        }
    }

    public ClaimResponseDTO findClaimById(Long claimId) {
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ClaimNotFoundException(claimId));
        return modelMapper.map(claim, ClaimResponseDTO.class);
    }
    public List<ClaimResponseDTO> findClaimsByUser(Long userId) {
        List<Claim> claims = claimRepository.findByUserId(userId);
        if (claims.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        return claims.stream()
                .map(claim -> modelMapper.map(claim, ClaimResponseDTO.class))
                .collect(Collectors.toList());
    }



    public List<ClaimResponseDTO> findAllClaims() {
        List<Claim> claims = claimRepository.findAll();
        return claims.stream()
                .map(claim -> modelMapper.map(claim, ClaimResponseDTO.class))
                .collect(Collectors.toList());
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
}


