package unit.worldwidewindsurfersweatherservice.domain

import spock.lang.Specification
import spock.lang.Subject
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditions
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditionsProvider
import worldwidewindsurfersweatherservice.domain.LocationWeatherConditionsService

import java.time.LocalDate

class LocationWeatherConditionsServiceUT extends Specification {

    LocationWeatherConditionsProvider locationWeatherConditionsProviderMock = Mock()

    @Subject
    LocationWeatherConditionsService service = new LocationWeatherConditionsService(locationWeatherConditionsProviderMock)

    def 'Should correctly return location with best conditions for given day'() {
        given:
            def day = LocalDate.of(2021, 07, 29)
            def location1 = new LocationWeatherConditions("location1", 5, 5)
            def location2 = new LocationWeatherConditions("location2", 35, 18)
            def location3 = new LocationWeatherConditions("location3", 15, 15.05)
        and:
            locationWeatherConditionsProviderMock.getConditionsForAllLocationsForDay(day) >> [location1, location2, location3]
        when:
            def result = service.getLocationWithGreatestConditionsForDay(day)
        then:
            result == location2
    }

    def 'Should return null when there is no location with conditions in range for given day'() {
        given:
            def day = LocalDate.of(2021, 07, 29)
            def location1 = new LocationWeatherConditions("location1", 4, 4)
            def location2 = new LocationWeatherConditions("location2", 36, 19)
            def location3 = new LocationWeatherConditions("location3", 0, 20)
        and:
            locationWeatherConditionsProviderMock.getConditionsForAllLocationsForDay(day) >> [location1, location2, location3]
        when:
            def result = service.getLocationWithGreatestConditionsForDay(day)
        then:
            result == null
    }

    def 'Should return null when there is no forecast for locations'() {
        given:
            def day = LocalDate.of(2021, 07, 29)
        and:
            locationWeatherConditionsProviderMock.getConditionsForAllLocationsForDay(day) >> []
        when:
            def result = service.getLocationWithGreatestConditionsForDay(day)
        then:
            result == null
    }
}
