package worldwidewindsurfersweatherservice.interfaces.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import worldwidewindsurfersweatherservice.interfaces.dto.LocationWeatherConditionsDtoFactory;

@Configuration
@Import({LocationWeatherConditionsDtoFactory.class})
public class RestConfig {
}
