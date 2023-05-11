package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Service.ClaimService;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import ClaimService.ClaimService.DTO.Response.ClaimResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/client")
public class ClaimController {

        private final ClaimService claimService;
        public ClaimController(ClaimService claimService) {
            this.claimService = claimService;
        }

        @PostMapping("/addclaim") // The specific URL path for adding a claim
        public ResponseEntity<String> addClaim(@RequestBody ClaimRequestDTO claimRequestDTO) {
            claimService.addclaim(claimRequestDTO);
            return ResponseEntity.ok("Claim added successfully");
        }
        @PutMapping("/updateclaim/{id}")
        public ResponseEntity<String> updateClaim(@PathVariable Long id, @RequestBody ClaimRequestDTO claimRequestDTO) {
        claimService.updateClaim(id, claimRequestDTO);
        return ResponseEntity.ok("Claim updated successfully");
        }

        @DeleteMapping("/deleteclaim/{id}")
        public ResponseEntity<String> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.ok("Claim deleted successfully");
        }

        @GetMapping("/findclaim/{id}")
        public ResponseEntity<ClaimResponseDTO> findClaimById(@PathVariable Long id) {
        ClaimResponseDTO claimResponseDTO = claimService.findClaimById(id);
        return ResponseEntity.ok(claimResponseDTO);
        }

        @GetMapping("/allclaims")
        public ResponseEntity<List<ClaimResponseDTO>> findAllClaims() {
        List<ClaimResponseDTO> claimResponseDTOList = claimService.findAllClaims();
        return ResponseEntity.ok(claimResponseDTOList);
        }
}








