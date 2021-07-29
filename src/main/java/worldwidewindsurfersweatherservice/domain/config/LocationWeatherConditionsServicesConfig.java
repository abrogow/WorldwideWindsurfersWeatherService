package worldwidewindsurfersweatherservice.domain.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditionsService;

@Configuration
@Import({LocationWeatherConditionsService.class})
public class LocationWeatherConditionsServicesConfig {
}
