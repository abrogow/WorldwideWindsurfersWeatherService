package worldwidewindsurfersweatherservice.interfaces.dto;

import lombok.NoArgsConstructor;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditions;

@NoArgsConstructor
public class LocationWeatherConditionsDtoFactory {

    public LocationWeatherConditionsDto create(LocationWeatherConditions weatherConditions) {
        return LocationWeatherConditionsDto.builder()
                .location(weatherConditions.getLocation().getLocation())
                .windSpeed(weatherConditions.getWindSpeed())
                .averageTemperature(weatherConditions.getAverageTemperature())
                .build();
    }
}
