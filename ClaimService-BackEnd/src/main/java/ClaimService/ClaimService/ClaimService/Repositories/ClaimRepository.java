package ClaimService.ClaimService.ClaimService.Repositories;

import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim,Long> {

    List<Claim> findAll();
    List<Claim> findByUserId(Long userId);

    Optional<Claim> findById(Long id);
}
