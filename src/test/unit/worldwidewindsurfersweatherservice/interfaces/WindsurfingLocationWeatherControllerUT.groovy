package worldwidewindsurfersweatherservice.interfaces

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditions
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditionsService
import worldwidewindsurfersweatherservice.shared.DateProvider

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@ContextConfiguration(classes = WindsurfingLocationWeatherController)
class WindsurfingLocationWeatherControllerUT extends Specification {

    @SpringBean
    LocationWeatherConditionsService serviceMock = Mock()

    @SpringBean
    DateProvider dateProviderMock = Mock() {
        now() >> LocalDate.of(2021, 01, 17)
    }

    @Autowired
    MockMvc mockMvc

    def 'Should return status 200 OK and location with best weather conditions'() {
        given:
            def day = LocalDate.of(2021, 01, 17)
            def location = new LocationWeatherConditions('test-location', 15, 12)
            def path = '/weather_forecasts/greatest_conditions?day=2021-01-17'
        and:
            serviceMock.getLocationWithGreatestConditionsForDay(day) >> location
        expect:
            mockMvc.perform(get(path))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath('location').value('test-location'))
                    .andExpect(jsonPath('averageTemperature').value(15))
                    .andExpect(jsonPath('windSpeed').value(12))
    }

    def 'Should return status 404 NOT_FOUND when there is no locations for given day'() {
        given:
            def day = LocalDate.of(2021, 01, 17)
            def path = '/weather_forecasts/greatest_conditions?day=2021-01-17'
        and:
            serviceMock.getLocationWithGreatestConditionsForDay(day) >> null
        expect:
            mockMvc.perform(get(path))
                    .andExpect(status().isNotFound())
    }

    def 'Should return status 400 BAD_REQUEST when date is out or range'() {
        given:
            def path = '/weather_forecasts/greatest_conditions?day=2021-01-16'
        expect:
            mockMvc.perform(get(path))
                    .andExpect(status().isBadRequest())
    }
}
