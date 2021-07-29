package worldwidewindsurfersweatherservice.integration.yaml;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WindsurfingLocation {

    private final String location;
    private final double latitude;
    private final double longitude;
}
