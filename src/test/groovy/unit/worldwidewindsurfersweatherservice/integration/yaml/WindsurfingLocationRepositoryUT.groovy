package unit.worldwidewindsurfersweatherservice.integration.yaml

import org.junit.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Subject
import worldwidewindsurfersweatherservice.integration.yaml.WindsurfingLocationRepository
import worldwidewindsurfersweatherservice.integration.yaml.config.WindsurfingLocationRepositoryConfig

@ContextConfiguration(classes = [WindsurfingLocationRepositoryConfig.class])
class WindsurfingLocationRepositoryUT extends Specification {

    @Subject
    @Autowired
    WindsurfingLocationRepository repository

//    def 'Should correctly load list of objects from yaml file'() {
//        when:
//            def result = repository.getAllLocations()
//        then:
//            result.size() == 1
//            result[0].location == 'Jastarnia'
//            result[0].latitude == 54.702839
//            result[0].longitude == 18.670719
//    }
}
