package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Exception.ProductNotFoundException;
import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Repositories.ProductRepository;
import ClaimService.ClaimService.ClaimService.Service.ClaimService;
import ClaimService.ClaimService.ClaimService.Validations.ClaimValidator;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import ClaimService.ClaimService.DTO.Response.ClaimResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/client")
public class ClaimController {

        private final ClaimService claimService;
        private final ClaimValidator claimValidator;
        private final ProductRepository productRepository;
        public ClaimController(ClaimService claimService, ClaimValidator claimValidator, ProductRepository productRepository) {
                this.claimService = claimService;
                this.claimValidator = claimValidator;
                this.productRepository = productRepository;
        }

        @PostMapping("/addclaim")
        public ResponseEntity<String> addClaim(@RequestBody ClaimRequestDTO claimRequestDTO, BindingResult bindingResult) {
                Optional<Product> productOptional = productRepository.findById(claimRequestDTO.getProductId());
                if (productOptional.isEmpty()) {
                        throw new ProductNotFoundException(claimRequestDTO.getProductId());
                }
                claimValidator.validate(claimRequestDTO, bindingResult);
                if (bindingResult.hasErrors()) {
                        return ResponseEntity.badRequest().body("Validation errors occurred");
                }

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








