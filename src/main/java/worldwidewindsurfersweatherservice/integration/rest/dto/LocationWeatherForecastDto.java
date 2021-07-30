package worldwidewindsurfersweatherservice.integration.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationWeatherForecastDto {

    @JsonProperty("valid_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate validDate;

    @JsonProperty("temp")
    double temp;

    @JsonProperty("wind_spd")
    double windSpd;
}
