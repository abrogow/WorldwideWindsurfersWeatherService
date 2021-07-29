package worldwidewindsurfersweatherservice.integration.yaml.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

    public static final String WINDSURFING_LOCATIONS_CACHE_NAME = "windsurfingLocationsCache";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(WINDSURFING_LOCATIONS_CACHE_NAME);
    }
}
