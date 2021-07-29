package worldwidewindsurfersweatherservice.integration.yaml.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocationRepository;

@Configuration
@Import({WindsurfingLocationRepository.class})
public class WindsurfingLocationRepositoryConfig {
}
