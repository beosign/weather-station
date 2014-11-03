package de.beosign.weatherstation.web;

import java.util.Arrays;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.ac.lr.LivingRoomHttpProperties;
import de.beosign.weatherstation.ac.owm.OpenWeatherMapHttpProperties;
import de.beosign.weatherstation.properties.DatabaseProperties;

/**
 * This class is used when running in a separate servlet container like TOMCAT.
 * 
 * @author Florian Dahlmanns
 */
public class WeatherStationServletInitializer extends SpringBootServletInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherStationServletInitializer.class);

    /**
     * Called before the application is started. You could set a profile here, for example.
     *
     * @param application the application
     * @return the spring application builder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        LOGGER.debug("Creating Spring Web Application");
        SpringApplicationBuilder b = application.sources(Application.class);

        return b;
    }

    /**
     * Called after the application is initialized.
     *
     * @param servletContext the servlet context
     * @return the web application context
     */
    @Override
    protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        WebApplicationContext ctx = super.createRootApplicationContext(servletContext);

        LOGGER.debug("Application started");
        LOGGER.info("Profiles: " + Arrays.toString(ctx.getEnvironment().getActiveProfiles()));

        LOGGER.info("OpenWeather HTTP Properties: " + ctx.getBean(OpenWeatherMapHttpProperties.class));
        LOGGER.info("LivingRoom HTTP Properties: " + ctx.getBean(LivingRoomHttpProperties.class));
        LOGGER.info("Datasource Properties" + ctx.getBean(DatabaseProperties.class));

        return ctx;
    }
}
