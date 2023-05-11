package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Service.ClaimService;
import ClaimService.ClaimService.DTO.Request.ClaimDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/addclaim")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }
        @PostMapping
        public ResponseEntity<String> createClaim(@RequestBody ClaimDTO claimDTO) {
            try {
                claimService.addClaim(claimDTO);
                return ResponseEntity.ok("Claim created successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create claim");
            }
        }
    }






