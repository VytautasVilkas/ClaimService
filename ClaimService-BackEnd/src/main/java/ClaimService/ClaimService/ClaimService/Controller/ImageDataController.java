package ClaimService.ClaimService.ClaimService.Controller;

import ClaimService.ClaimService.ClaimService.Models.ImageData;
import ClaimService.ClaimService.ClaimService.Repositories.ImageDataRepository;
import ClaimService.ClaimService.ClaimService.Service.ImageDataService;
import ClaimService.ClaimService.DTO.Response.ImageUploadResponse;
import ClaimService.ClaimService.Util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/image")
public class ImageDataController {
    @Autowired
    private ImageDataService imageDataService;
    private final ImageDataRepository imageDataRepository;

    public ImageDataController(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        ImageUploadResponse response = imageDataService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    @GetMapping("/info/{name}")
    public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
        ImageData image = imageDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") Long id) {
        byte[] imageBytes = imageDataService.getImageById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // Assuming the image is in PNG format

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

}