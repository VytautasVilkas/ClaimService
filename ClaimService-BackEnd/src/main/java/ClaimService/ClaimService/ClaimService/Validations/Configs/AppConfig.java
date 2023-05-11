package ClaimService.ClaimService.ClaimService.Validations.Configs;

import ClaimService.ClaimService.ClaimService.Validations.ClaimValidator;
import ClaimService.ClaimService.ClaimService.Validations.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }
    @Bean
    public ClaimValidator claimValidator() {
        return new ClaimValidator();
    }
}