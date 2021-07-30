package worldwidewindsurfersweatherservice.interfaces

import com.github.tomakehurst.wiremock.client.WireMock
import org.spockframework.spring.SpringBean
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import spock.lang.Specification
import worldwidewindsurfersweatherservice.shared.DateProvider

import java.time.LocalDate

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.equalTo

@FunctionalTest
@AutoConfigureWireMock(port = 8080)
class WindsurfingLocationWeatherControllerFT extends Specification {

    def GET_FORECAST_BY_LOCATION_PATH = '/forecast/daily?lat=54.702839&lon=18.670719&key=test'

    @SpringBean
    DateProvider dateProviderMock = Mock() {
        now() >> LocalDate.of(2021, 01, 17)
    }

    @LocalServerPort
    int localServerPort

    def setup() {
        WireMock.removeAllMappings()
        WireMock.resetAllRequests()
        mockGetForecastByLocationEndpoint()
    }

    def 'Should return best weather conditions fetched from external api'() {
        given:
            def path = '/weather_forecasts/greatest_conditions?day=2021-01-17'
        expect:
            given().port(localServerPort).get(path)
                    .then().statusCode(200)
                    .and().body("location", equalTo('Jastarnia'))
                    .and().body("averageTemperature", equalTo(31.4F))
                    .and().body("windSpeed", equalTo(6.06129F))
    }

    def mockGetForecastByLocationEndpoint() {
        WireMock.stubFor(WireMock.get(GET_FORECAST_BY_LOCATION_PATH)
                        .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody('''{
                                            "data": [
                                                {
                                                    "wind_spd": 6.06129,
                                                    "valid_date": "2021-01-17",
                                                    "temp": 31.4
                                                }
                                            ]
                                        }''')
                        .withStatus(200)))
    }
}
