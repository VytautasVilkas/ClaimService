package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Exception.ProductNotFoundException;
import ClaimService.ClaimService.ClaimService.Repositories.ImageDataRepository;
import ClaimService.ClaimService.ClaimService.Repositories.ProductRepository;
import ClaimService.ClaimService.ClaimService.Service.ClaimService;
import ClaimService.ClaimService.ClaimService.Service.ImageDataService;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import ClaimService.ClaimService.DTO.Request.ClaimUpdateDTO;
import ClaimService.ClaimService.DTO.Response.ClaimResponseDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/client")
public class ClaimController {

        private final ClaimService claimService;
        private final ProductRepository productRepository;
        private final ModelMapper modelMapper;
        private final ImageDataRepository imageDataRepository;
        private final ImageDataService imageDataService;



        public ClaimController(ClaimService claimService, ProductRepository productRepository, ModelMapper modelMapper, ImageDataRepository imageDataRepository, ImageDataService imageDataService) {
                this.claimService = claimService;
                this.productRepository = productRepository;
                this.modelMapper = modelMapper;
                this.imageDataRepository = imageDataRepository;
                this.imageDataService = imageDataService;

        }

        @PostMapping("/addclaim")
        public ResponseEntity<?> addClaim(@Valid @RequestBody ClaimRequestDTO claimRequestDTO, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    validationErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(validationErrors);
            }
            try {
                ClaimResponseDTO claim = claimService.addClaim(claimRequestDTO);
                return ResponseEntity.ok("Claim added successfully.");
            } catch (ProductNotFoundException e) {
                Map<String, String> validationErrors = new HashMap<>();
                validationErrors.put("productId", "Product not found.");
                return ResponseEntity.badRequest().body(validationErrors);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add claim.");
            }
        }
        @PutMapping("/updateclaim/{id}")
        public ResponseEntity<?> updateClaim(@PathVariable Long id,@Valid @RequestBody ClaimUpdateDTO claimUpdateDTO,BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                Map<String, String> validationErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    validationErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(validationErrors);
            }
            try {
                ClaimResponseDTO updatedClaim = claimService.updateClaim(id, claimUpdateDTO);
                return ResponseEntity.ok(updatedClaim);
            }catch (ProductNotFoundException e) {
                Map<String, String> validationErrors = new HashMap<>();
                validationErrors.put("productId", "Product not found.");
                return ResponseEntity.badRequest().body(validationErrors);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add claim.");
            }
        }

        @DeleteMapping("/deleteclaim/{id}")
        public ResponseEntity<String> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.ok("Claim deleted successfully");
        }
        @Transactional
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
    @Transactional
    @GetMapping("/findClaimsByUser/{userId}")
    public ResponseEntity<List<ClaimResponseDTO>> findClaimByUser(@PathVariable Long userId) {
        List<ClaimResponseDTO> claimResponseDTO = claimService.findClaimsByUser(userId);
        return ResponseEntity.ok(claimResponseDTO);
    }






}








