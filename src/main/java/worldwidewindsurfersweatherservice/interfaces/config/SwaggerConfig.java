package worldwidewindsurfersweatherservice.interfaces.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("“Worldwide Windsurfer’s Weather Service")
                                .description("REST API documentation")
                );
    }
}
