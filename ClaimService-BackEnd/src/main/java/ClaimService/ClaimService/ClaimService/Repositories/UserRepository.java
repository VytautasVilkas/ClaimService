package ClaimService.ClaimService.ClaimService.Repositories;


import ClaimService.ClaimService.ClaimService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User>findById(Long userId);
    User findByUsername(String userName);

    List<User> findAll();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}

