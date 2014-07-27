package de.beosign.weatherstation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ecyrd.speed4j.StopWatchFactory;

import de.beosign.weatherstation.properties.DatabaseProperties;
import de.beosign.weatherstation.properties.HttpProperties;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static StopWatchFactory stopWatchFactory = StopWatchFactory.getInstance("loggingFactory");

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        LOGGER.debug("Application started");

        LOGGER.info("HTTP Properties: " + ctx.getBean(HttpProperties.class));
        LOGGER.info("Datasource Properties" + ctx.getBean(DatabaseProperties.class));
    }

    public static StopWatchFactory getStopWatchFactory() {
        return stopWatchFactory;
    }

}
