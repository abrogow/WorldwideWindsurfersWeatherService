package worldwidewindsurfersweatherservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocation;

@Getter
@RequiredArgsConstructor
public class LocationWeatherConditions {

    private final WindsurfingLocation location;

    private final int averageTemperature;

    private final double windSpeed;
}
