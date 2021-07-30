package worldwidewindsurfersweatherservice.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import worldwidewindsurfersweatherservice.shared.DateProvider;

@Configuration
@Import({DateProvider.class})
public class DateConfig {
}
