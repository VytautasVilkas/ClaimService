package ClaimService.ClaimService.ClaimService.Configs;

import ClaimService.ClaimService.ClaimService.Models.Claim;
import ClaimService.ClaimService.ClaimService.Models.ImageData;
import ClaimService.ClaimService.DTO.Request.ClaimRequestDTO;
import ClaimService.ClaimService.DTO.Request.ClaimUpdateDTO;
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
				skip(destination.getId()); // Skip mapping for the id property
			}
		});
		modelMapper.addMappings(new PropertyMap<ClaimUpdateDTO, Claim>() {
			protected void configure() {
				map().setUser(null); // Skip mapping for the user property
				map().setImages(null); // Skip mapping for the images property
			}
		});

		return modelMapper;
	}
}



