package worldwidewindsurfersweatherservice.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class LocationWeatherConditionsDto {

    private final String location;

    private final int averageTemperature;

    private final double windSpeed;
}
