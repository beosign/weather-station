package de.beosign.weatherstation;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ecyrd.speed4j.StopWatchFactory;

import de.beosign.weatherstation.ac.lr.LivingRoomHttpProperties;
import de.beosign.weatherstation.ac.owm.OpenWeatherMapHttpProperties;
import de.beosign.weatherstation.logging.Log;
import de.beosign.weatherstation.properties.DatabaseProperties;

/**
 * Main class.
 * 
 * @author Florian Dahlmanns
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan
public class Application {
    private static StopWatchFactory stopWatchFactory = StopWatchFactory.getInstance("loggingFactory");

    /**
     * Spring does not work if this is private.
     */
    protected Application() {

    }

    /**
     * Main method.
     * 
     * @param args args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        Log.logger().debug("Application started");
        Log.logger().info("Profiles: " + Arrays.toString(ctx.getEnvironment().getActiveProfiles()));

        Log.logger().info("LR HTTP Properties: " + ctx.getBean(LivingRoomHttpProperties.class));
        Log.logger().info("OW HTTP Properties: " + ctx.getBean(OpenWeatherMapHttpProperties.class));

        Log.logger().info("Datasource Properties" + ctx.getBean(DatabaseProperties.class));
    }

    /**
     * Returns Stopwatchfactory.
     * 
     * @return Stopwatchfactory
     */
    public static StopWatchFactory getStopWatchFactory() {
        return stopWatchFactory;
    }

}
