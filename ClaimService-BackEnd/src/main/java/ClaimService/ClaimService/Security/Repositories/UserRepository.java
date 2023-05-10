package ClaimService.ClaimService.Security.Repositories;

import ClaimService.ClaimService.Security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> FindByUsername(String username);
}
