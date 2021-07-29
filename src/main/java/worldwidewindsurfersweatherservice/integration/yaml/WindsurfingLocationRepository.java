package worldwidewindsurfersweatherservice.integration.yaml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import worldwidewindsurfersweatherservice.integration.yaml.config.CacheConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class WindsurfingLocationRepository {

    private final static String WINDSURFING_LOCATION_FILE_LOCATION = "src/main/resources/windsurfing_locations.yaml";

    @Cacheable(CacheConfig.WINDSURFING_LOCATIONS_CACHE_NAME)
    public List<WindsurfingLocation> getAllLocations() {
        try {
            log.info("Start fetching list of windsurfing locations");
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            return  mapper.readValue(new File(WINDSURFING_LOCATION_FILE_LOCATION), new TypeReference<List<WindsurfingLocation>>(){});
        } catch (IOException e) {
            log.error("Received error during fetching list of locations");
            throw new IllegalStateException("Failed to fetch list of windsurfing locations");
        }
    }
}
