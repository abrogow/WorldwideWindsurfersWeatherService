package worldwidewindsurfersweatherservice.shared;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class DateProvider {

    public LocalDate now() {
        return LocalDate.now();
    }
}
