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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class WindsurfingLocationRepository {

    private final static String WINDSURFING_LOCATION_FILE_LOCATION = "windsurfing_locations.yaml";

    @Cacheable(CacheConfig.WINDSURFING_LOCATIONS_CACHE_NAME)
    public List<WindsurfingLocation> getAllLocations() {
        log.info("Start fetching list of windsurfing locations");
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(WINDSURFING_LOCATION_FILE_LOCATION).getFile());

            byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
            String content =  new String(encoded, StandardCharsets.US_ASCII);


            return  mapper.readValue(file, new TypeReference<List<WindsurfingLocation>>(){});
        } catch (IOException e) {
            log.error("Received error during fetching list of locations");
            throw new IllegalStateException("Failed to fetch list of windsurfing locations");
        }
    }
}
