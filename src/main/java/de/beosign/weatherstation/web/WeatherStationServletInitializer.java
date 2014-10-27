package de.beosign.weatherstation.web;

import java.util.Arrays;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.properties.DatabaseProperties;
import de.beosign.weatherstation.properties.HttpProperties;

/**
 * This class is used when running in a seperate servlet container like TOMCAT.
 * The {@link #configure(SpringApplicationBuilder)} method will be called first.
 * The {@link #createRootApplicationContext(ServletContext)} will be called when the application has
 * initialized.
 * 
 * @author Florian Dahlmanns
 */
public class WeatherStationServletInitializer extends SpringBootServletInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherStationServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        LOGGER.debug("Creating Spring Web Application");
        SpringApplicationBuilder b = application.sources(Application.class);

        return b;
    }

    @Override
    protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        WebApplicationContext ctx = super.createRootApplicationContext(servletContext);

        LOGGER.debug("Application started");
        LOGGER.info("Profiles: " + Arrays.toString(ctx.getEnvironment().getActiveProfiles()));

        LOGGER.info("HTTP Properties: " + ctx.getBean(HttpProperties.class));
        LOGGER.info("Datasource Properties" + ctx.getBean(DatabaseProperties.class));

        return ctx;
    }
}
