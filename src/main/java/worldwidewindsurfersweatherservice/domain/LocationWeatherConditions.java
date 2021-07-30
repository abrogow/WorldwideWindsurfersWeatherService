package worldwidewindsurfersweatherservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LocationWeatherConditions {

    private final String location;

    private final double averageTemperature;

    private final double windSpeed;
}
