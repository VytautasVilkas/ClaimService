package ClaimService.ClaimService.ClaimService.Service;

import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.ClaimService.Repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;
    public Claim addClaim(Claim claim) {
            return claimRepository.save(claim);
    }
    public Claim updateClaim(Claim claim) {
            return claimRepository.save(claim);
    }
    public List<Claim> getAllClaims() {
            return claimRepository.findAll();
    }
    public Claim getClaimById(Long id) {
            Optional<Claim> optionalClaim = claimRepository.findById(id);
            return optionalClaim.orElse(null);
    }
    public Claim addProductToClaim(Long claimId, Product product) {
        Claim claim = getClaimById(claimId);
        if (claim != null) {
                product.setClaim(claim);
                claim.getProducts().add(product);
                updateClaim(claim);
        }
        return claim;
    }
}
