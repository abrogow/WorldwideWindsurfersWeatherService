package worldwidewindsurfersweatherservice.interfaces;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditions;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditionsService;
import worldwidewindsurfersweatherservice.shared.DateProvider;

import java.time.LocalDate;

@Tag(name = "Windsurfing Location Weather Controller")
@RestController
@RequestMapping("/weather_forecasts")
@RequiredArgsConstructor
public class WindsurfingLocationWeatherController {

    private static final int TIME_PERIOD_IN_DAYS = 17;
    private final LocationWeatherConditionsService locationWeatherConditionsService;
    private final DateProvider dateProvider;

    @GetMapping(path = "/greatest_conditions",
            produces = "application/json; charset=UTF-8")
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Bad request. Data should be in range <today, today + 16 days)"),
                            @ApiResponse(responseCode = "404", description = "Location with best conditions not found")})
    public ResponseEntity<LocationWeatherConditions> getLocationWithGreatestConditionsForDay(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate day) {
        if(validDate(day)) {
            return getLocationWithGreatestConditionsForValidDay(day);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private ResponseEntity<LocationWeatherConditions> getLocationWithGreatestConditionsForValidDay(LocalDate day) {
        LocationWeatherConditions locationWithBestConditions = locationWeatherConditionsService.getLocationWithGreatestConditionsForDay(day);

        if(locationWithBestConditions != null) {
            return ResponseEntity.ok(locationWithBestConditions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean validDate(LocalDate day) {
        LocalDate minDate = dateProvider.now().minusDays(1);
        LocalDate maxDate = minDate.plusDays(TIME_PERIOD_IN_DAYS);
        return maxDate.isAfter(day) && minDate.isBefore(day);
    }
}
