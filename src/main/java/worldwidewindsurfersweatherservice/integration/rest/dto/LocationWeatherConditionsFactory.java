package worldwidewindsurfersweatherservice.integration.rest.dto;

import lombok.NoArgsConstructor;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditions;
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocation;

@NoArgsConstructor
public class LocationWeatherConditionsFactory {

    public LocationWeatherConditions create(WindsurfingLocation location, LocationWeatherForecastDto locationWeatherForecastDto) {
        return new LocationWeatherConditions(location.getLocation(), locationWeatherForecastDto.getTemp(), locationWeatherForecastDto.getWindSpd());
    }
}
