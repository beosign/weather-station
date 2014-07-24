package de.beosign.weatherstation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ecyrd.speed4j.StopWatchFactory;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static StopWatchFactory stopWatchFactory = StopWatchFactory.getInstance("loggingFactory");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.debug("Application started");
    }

    public static StopWatchFactory getStopWatchFactory() {
        return stopWatchFactory;
    }
}
