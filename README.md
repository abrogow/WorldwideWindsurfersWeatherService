# WorldwideWindsurfersWeatherService

# Run application in docker container:

First run:
`$ mvn clean`
`$ mvn install`
`$ docker build . -t myapp`
`$ docker run -p 8080:8080 -t myapp`

API is available under: `http://localhost:8080/swagger-ui.html`