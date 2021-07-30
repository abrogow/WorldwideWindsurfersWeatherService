package worldwidewindsurfersweatherservice.integration.rest

import feign.FeignException
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject
import worldwidewindsurfersweatherservice.integration.rest.config.RestClientConfig
import worldwidewindsurfersweatherservice.integration.rest.dto.LocationWeatherForecastDto
import worldwidewindsurfersweatherservice.integration.rest.dto.WeatherForecastDto
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocation
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocationRepository

import java.time.LocalDate

@ContextConfiguration(classes = RestClientConfig)
class WeatherForecastServiceUT extends Specification {

    @SpringBean
    WindsurfingLocationRepository windsurfingLocationRepositoryMock = Mock()

    @SpringBean
    WeatherForecastFeignClient feignClientMock = Mock()

    @Subject
    @Autowired
    WeatherForecastService weatherForecastService

    def 'Should correctly fetch forecast for all locations'() {
        given:
            def day = LocalDate.of(2021, 01, 01)
            def jastarnia = new WindsurfingLocation('Jastarnia', 54.702839, 18.670719)
            def bridgeton = new WindsurfingLocation('Bridgetown', 13.096851, -59.614483)
            windsurfingLocationRepositoryMock.getAllLocations() >> [jastarnia, bridgeton]
        and:
            def forecastForJastarnia = new WeatherForecastDto([new LocationWeatherForecastDto(day, 12, 13),
                                                               new LocationWeatherForecastDto(LocalDate.of(2021, 01, 02), 13, 14)])
            def forecastForBridgeton = new WeatherForecastDto([new LocationWeatherForecastDto(day, 45, 15),
                                                               new LocationWeatherForecastDto(LocalDate.of(2021, 01, 02), 2, 0)])
            feignClientMock.getForecastByLocation(jastarnia.getLatitude(), jastarnia.getLongitude(), _) >> forecastForJastarnia
            feignClientMock.getForecastByLocation(bridgeton.getLatitude(), bridgeton.getLongitude(), _) >> forecastForBridgeton
        when:
            def result = weatherForecastService.getConditionsForAllLocationsForDay(day)
        then:
            result.size() == 2
            result[0].location == 'Jastarnia'
            result[0].averageTemperature == 12
            result[0].windSpeed == 13
            result[1].location == 'Bridgetown'
            result[1].averageTemperature == 45
            result[1].windSpeed == 15
    }

    def 'Should return empty list when there is no forecast for given day'() {
        given:
            def day = LocalDate.of(2021, 01, 21)
            def jastarnia = new WindsurfingLocation('Jastarnia', 54.702839, 18.670719)
            def bridgeton = new WindsurfingLocation('Bridgetown', 13.096851, -59.614483)
            windsurfingLocationRepositoryMock.getAllLocations() >> [jastarnia, bridgeton]
        and:
            def forecastForJastarnia = new WeatherForecastDto([new LocationWeatherForecastDto(LocalDate.of(2021, 01, 02), 13, 14)])
            def forecastForBridgeton = new WeatherForecastDto([new LocationWeatherForecastDto(LocalDate.of(2021, 01, 02), 2, 0)])
            feignClientMock.getForecastByLocation(jastarnia.getLatitude(), jastarnia.getLongitude(), _) >> forecastForJastarnia
            feignClientMock.getForecastByLocation(bridgeton.getLatitude(), bridgeton.getLongitude(), _) >> forecastForBridgeton
        when:
            def result = weatherForecastService.getConditionsForAllLocationsForDay(day)
        then:
            result == []
    }

    def 'Should throw exception when something wrong during fetching data from external service'() {
        given:
            def day = LocalDate.of(2021, 01, 21)
            def jastarnia = new WindsurfingLocation('Jastarnia', 54.702839, 18.670719)
            def bridgeton = new WindsurfingLocation('Bridgetown', 13.096851, -59.614483)
            windsurfingLocationRepositoryMock.getAllLocations() >> [jastarnia, bridgeton]
        and:
            feignClientMock.getForecastByLocation(jastarnia.getLatitude(), jastarnia.getLongitude(), _) >> new FeignException(400, "Bad request")
        when:
            weatherForecastService.getConditionsForAllLocationsForDay(day)
        then:
            def e = thrown(IllegalArgumentException)
            e.message == 'Failed to fetch weather forecast for given location'
    }
}
