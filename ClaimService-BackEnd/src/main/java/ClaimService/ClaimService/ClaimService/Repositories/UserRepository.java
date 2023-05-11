package ClaimService.ClaimService.ClaimService.Repositories;

import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Models.User;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(java.util.UUID userId);
}

