package worldwidewindsurfersweatherservice.integration.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastDto {

    List<LocationWeatherForecastDto> data;

}
