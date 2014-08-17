package de.beosign.weatherstation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import de.beosign.weatherstation.Application;

public class WeatherStationServletInitializer extends SpringBootServletInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherStationServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        SpringApplicationBuilder b = application.sources(Application.class);

        // LOGGER.debug("Active profiles: " + Arrays.deepToString(b.context().getEnvironment().getActiveProfiles()));

        return b;
    }
}
