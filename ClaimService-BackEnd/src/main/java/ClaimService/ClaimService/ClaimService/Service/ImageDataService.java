package ClaimService.ClaimService.ClaimService.Service;


import ClaimService.ClaimService.ClaimService.Models.ImageData;
import ClaimService.ClaimService.ClaimService.Repositories.ImageDataRepository;
import ClaimService.ClaimService.DTO.Response.ImageUploadResponse;
import ClaimService.ClaimService.Util.ImageUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




import java.io.IOException;
import java.util.Optional;


@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;
    public ImageDataService(ImageDataRepository imageDataRepository) {this.imageDataRepository = imageDataRepository;
    }
        public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {
            ImageData imageData = ImageData.builder()
                    .name(file.getOriginalFilename())
                    .imageData(file.getBytes())
                    .build();

            imageDataRepository.save(imageData);

            return new ImageUploadResponse("Image uploaded successfully.", imageData.getName());
        }
    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }
    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }


}
