package ClaimService.ClaimService.ClaimService.Repositories;

import ClaimService.ClaimService.ClaimService.Models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.Optional;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
        Optional<ImageData> findByName(String name);
        Optional<ImageData> findById(Long id);


}

