package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Models.User;
import ClaimService.ClaimService.ClaimService.Repositories.ClaimRepository;
import ClaimService.ClaimService.ClaimService.Repositories.ProductRepository;
import ClaimService.ClaimService.ClaimService.Repositories.UserRepository;
import ClaimService.ClaimService.DTO.Request.ClaimDTO;
import ClaimService.ClaimService.DTO.Request.UserRequestDTO;
import ClaimService.ClaimService.DTO.Response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;


    public void addClaim(ClaimDTO claimDTO) {
        Claim claim = new Claim();
        claim.setMessage(claimDTO.getMessage());
        claim.setDamage(claimDTO.getDamage());
        claim.setPhotoData(claimDTO.getPhotoData());
        Optional<Product> productOptional = productRepository.findById(claimDTO.getProductId());
        productOptional.ifPresent(claim::setProduct);
        claimRepository.save(claim);
    }

}

