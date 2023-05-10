package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Models.Product;
import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.ClaimService.Repositories.ProductRepository;
import ClaimService.ClaimService.ClaimService.Service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user{userId}")
public class ClaimController {

    @Autowired
    private ClaimService claimService;
    @Autowired
    private ProductRepository productrepository;

    @PostMapping("/addclaim")
    public ResponseEntity<Claim> addClaim(@RequestBody Claim claim) {
        claimService.addClaim(claim);
        return new ResponseEntity<>(claim, HttpStatus.CREATED);
    }



    @PostMapping("/{claimId}/products")
    public ResponseEntity<Claim> addProductToClaim(@PathVariable Long claimId, @RequestBody Product product) {
        Claim claim = claimService.addProductToClaim(claimId, product);
        if (claim == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(claim, HttpStatus.OK);
    }

    @GetMapping("/claims")
    public ResponseEntity<List<Claim>> getAllClaims() {
        List<Claim> claims = claimService.getAllClaims();
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }

    @GetMapping("/claims/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        Claim claim = claimService.getClaimById(id);
        if (claim == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(claim, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable Long id, @RequestBody Claim claim) {
        Claim existingClaim = claimService.getClaimById(id);
        if (existingClaim == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingClaim.setMessage(claim.getMessage());
        existingClaim.setDamage(claim.getDamage());
        claimService.updateClaim(existingClaim);
        return new ResponseEntity<>(existingClaim, HttpStatus.OK);
    }
}


