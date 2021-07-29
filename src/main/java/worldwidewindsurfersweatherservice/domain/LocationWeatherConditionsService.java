package worldwidewindsurfersweatherservice.domain;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class LocationWeatherConditionsService {

    private final static int WIND_SPEED_SCALER = 3;

    private final static double WIND_SPEED_MIN = 5;
    private final static double WIND_SPEED_MAX = 18;

    private final static int AVERAGE_TEMP_MIN = 5;
    private final static int AVERAGE_TEMP_MAX = 35;

    private final LocationWeatherConditionsProvider locationWeatherConditionsProvider;


    public LocationWeatherConditions getLocationWithGreatestConditionsForDay(LocalDate day) {
        List<LocationWeatherConditions> locationWeatherConditionList = locationWeatherConditionsProvider.getConditionsForAllLocationsForDay(day);
        List<LocationWeatherConditions> filteredLocations = filterLocations(locationWeatherConditionList);
        Optional<LocationWeatherConditions> greatestLocation = filteredLocations.stream().max(Comparator.comparingDouble(this::conditionsScore));

        return greatestLocation.orElse(null);
    }

    private List<LocationWeatherConditions> filterLocations(List<LocationWeatherConditions> locationWeatherConditionList) {
        return locationWeatherConditionList.stream()
                .filter(conditions -> windSpeedInRange(conditions.getWindSpeed()))
                .filter(conditions -> temperatureInRange(conditions.getAverageTemperature()))
                .collect(Collectors.toList());
    }

    private boolean windSpeedInRange(double windSpeed) {
        return windSpeed >= WIND_SPEED_MIN && windSpeed <= WIND_SPEED_MAX;
    }

    private boolean temperatureInRange(int averageTemperature) {
        return averageTemperature >= AVERAGE_TEMP_MIN && averageTemperature <= AVERAGE_TEMP_MAX;
    }

    private double conditionsScore(LocationWeatherConditions locationWeatherConditions) {
        return WIND_SPEED_SCALER * locationWeatherConditions.getWindSpeed() + locationWeatherConditions.getAverageTemperature();
    }
}
