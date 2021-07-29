package worldwidewindsurfersweatherservice.integration.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditions;
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditionsProvider;
import worldwidewindsurfersweatherservice.integration.rest.dto.LocationWeatherConditionsFactory;
import worldwidewindsurfersweatherservice.integration.rest.dto.LocationWeatherForecastDto;
import worldwidewindsurfersweatherservice.integration.rest.dto.WeatherForecastDto;
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocation;
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class WeatherForecastService implements LocationWeatherConditionsProvider {

    private final WeatherForecastFeignClientProperties properties;
    private final WindsurfingLocationRepository windsurfingLocationRepository;
    private final WeatherForecastFeignClient feignClient;
    private final LocationWeatherConditionsFactory factory;

    @Override
    public List<LocationWeatherConditions> getConditionsForAllLocationsForDay(LocalDate day) {
        List<LocationWeatherConditions> locationWeatherConditionList = new ArrayList<>();

        List<WindsurfingLocation> availableLocations = windsurfingLocationRepository.getAllLocations();

        for(WindsurfingLocation location : availableLocations) {
            LocationWeatherConditions locationWeatherConditions = getLocationWeatherConditionsForDay(day, location);
            locationWeatherConditionList.add(locationWeatherConditions);
        }
        return locationWeatherConditionList;
    }

    private LocationWeatherConditions getLocationWeatherConditionsForDay(LocalDate day, WindsurfingLocation location) {

        WeatherForecastDto weatherForecastDto = getForecastByLocation(location);
        LocationWeatherForecastDto locationWeatherForecastDto = getForecastForDay(day, weatherForecastDto);
        return locationWeatherForecastDto != null ? factory.create(location, locationWeatherForecastDto) : null;
    }

    private WeatherForecastDto getForecastByLocation(WindsurfingLocation location) {
        try {
            log.info("Start fetching weather forecast for {}", location.getLocation());
            return feignClient.getForecastByLocation(location.getLatitude(),
                    location.getLongitude(), properties.getKey());
        } catch (Exception e) {
            log.error("Received error from Weather Forecast service");
            throw new IllegalArgumentException("Failed to fetch weather forecast for given location");
        }
    }

    private LocationWeatherForecastDto getForecastForDay(LocalDate day, WeatherForecastDto weatherForecastDto) {
        Optional<LocationWeatherForecastDto> locationWeatherForecastDto = weatherForecastDto.getData()
                .stream()
                .filter(forecast -> day.equals(forecast.getValidDate()))
                .findAny();

        return locationWeatherForecastDto.orElse(null);
    }
}
