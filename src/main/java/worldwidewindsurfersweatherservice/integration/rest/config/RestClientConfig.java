package worldwidewindsurfersweatherservice.integration.rest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import worldwidewindsurfersweatherservice.integration.rest.WeatherForecastFeignClient;
import worldwidewindsurfersweatherservice.integration.rest.WeatherForecastFeignClientProperties;
import worldwidewindsurfersweatherservice.integration.rest.WeatherForecastService;
import worldwidewindsurfersweatherservice.integration.rest.dto.LocationWeatherConditionsFactory;

@Configuration
@EnableFeignClients(clients = {WeatherForecastFeignClient.class})
@Import({LocationWeatherConditionsFactory.class,
        WeatherForecastService.class})
public class RestClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "weather-forecast.api")
    WeatherForecastFeignClientProperties weatherForecastFeignClientProperties() {
        return new WeatherForecastFeignClientProperties();
    }
}
