package ClaimService.ClaimService.ClaimService.Configs;

import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.ClaimService.Models.ImageData;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import ClaimService.ClaimService.DTO.Request.ClaimUpdateDTO;
import ClaimService.ClaimService.DTO.Response.ClaimResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		modelMapper.addMappings(new PropertyMap<ClaimRequestDTO, Claim>() {
			protected void configure() {
				skip(destination.getId());
			}
		});
		modelMapper.createTypeMap(Claim.class, ClaimResponseDTO.class)
				.addMapping(src -> src.getImages().getId(), ClaimResponseDTO::setPhotoId);

		modelMapper.addMappings(new PropertyMap<ClaimUpdateDTO, Claim>() {
			protected void configure() {
				map().setUser(null);
				map().setImages(null);
			}
		});

		return modelMapper;
	}
}



