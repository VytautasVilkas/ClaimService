package ClaimService.ClaimService.ClaimService.Repositories;


import ClaimService.ClaimService.ClaimService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findByUsername(String username);
    Optional<User>findById(Long userId);
    List<User> findAll();





}

