# WorldwideWindsurfersWeatherService

# Run application in docker container:

Build project:
`./mvnw clean build`

Build docker image:
`$ ./mvnw clean`
`$ ./mvnw install`
`$ docker build . -t myapp`

Run docker image:
`$ docker run -p 8080:8080 -t myapp`

API is available under: `http://localhost:8080/swagger-ui.html`