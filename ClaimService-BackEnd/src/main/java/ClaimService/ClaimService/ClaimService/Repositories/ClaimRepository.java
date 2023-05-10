package ClaimService.ClaimService.ClaimService.Repositories;

import ClaimService.ClaimService.ClaimService.Models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim,Long> {
}
