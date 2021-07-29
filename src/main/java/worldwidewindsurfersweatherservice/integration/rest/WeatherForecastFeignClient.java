package worldwidewindsurfersweatherservice.integration.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import worldwidewindsurfersweatherservice.integration.rest.dto.WeatherForecastDto;

@FeignClient(value = "watherForecast", url = "${weather-forecast.api.url}")
public interface WeatherForecastFeignClient {

    @GetMapping("/forecast/daily")
    WeatherForecastDto getForecastByLocation(@RequestParam("lat") double latitude, @RequestParam("lon") double longitude, @RequestParam("key") String apiKey);
}
