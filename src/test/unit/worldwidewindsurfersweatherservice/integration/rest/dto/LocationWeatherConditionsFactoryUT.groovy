package worldwidewindsurfersweatherservice.integration.rest.dto

import spock.lang.Specification
import spock.lang.Subject
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocation

import java.time.LocalDate

class LocationWeatherConditionsFactoryUT extends Specification {

    @Subject
    LocationWeatherConditionsFactory factory = new LocationWeatherConditionsFactory()

    def 'Should correctly create LocationWeatherConditions object'() {
        given:
            def location = new WindsurfingLocation("location", 11.0, 12.0)
            def forecast = new LocationWeatherForecastDto(LocalDate.of(2021, 01, 01), 12, 13.01)
        when:
            def result = factory.create(location, forecast)
        then:
            result.location == "location"
            result.windSpeed == 13.01
            result.averageTemperature == 12
    }
}
