package worldwidewindsurfersweatherservice.interfaces;

import feign.form.FormProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditionsService;
import worldwidewindsurfersweatherservice.interfaces.dto.LocationWeatherConditionsDto;
import worldwidewindsurfersweatherservice.interfaces.dto.LocationWeatherConditionsDtoFactory;

import java.time.LocalDate;

@Tag(name = "Windsurfing Location Weather Controller")
@RestController
@RequestMapping("/weather_forecasts")
@RequiredArgsConstructor
public class WindsurfingLocationWeatherController {

    private final LocationWeatherConditionsService locationWeatherConditionsService;
    private final LocationWeatherConditionsDtoFactory locationWeatherConditionsDtoFactory;

    @GetMapping(path = "/greatest_conditions",
            produces = "application/json; charset=UTF-8")
    public LocationWeatherConditionsDto getLocationWithGreatestConditionsForDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day) {
        return locationWeatherConditionsDtoFactory.create(
                locationWeatherConditionsService.getLocationWithGreatestConditionsForDay(day));
    }
}
