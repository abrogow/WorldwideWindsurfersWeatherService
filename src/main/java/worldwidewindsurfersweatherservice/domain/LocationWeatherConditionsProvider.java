package worldwidewindsurfersweatherservice.domain;

import java.time.LocalDate;
import java.util.List;

public interface LocationWeatherConditionsProvider {

    List<LocationWeatherConditions> getConditionsForAllLocationsForDay(LocalDate day);
}
