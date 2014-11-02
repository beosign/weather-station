package de.beosign.test.weatherstation.common;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import de.beosign.test.weatherstation.rest.RestReading;
import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.ac.lr.LivingRoomHttpProperties;
import de.beosign.weatherstation.ac.owm.OpenWeatherMapHttpProperties;
import de.beosign.weatherstation.properties.DatabaseProperties;
import de.beosign.weatherstation.spring.SpringProfiles;

/**
 * Sets up Spring {@link org.springframework.context.ApplicationContext} and a logger.
 * 
 * @author florian
 */
public class JUnitUtil {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RestReading.class);
    private static ConfigurableApplicationContext context;

    /**
     * Checkstyle complains when this is missing.
     */
    protected JUnitUtil() {
    }

    /**
     * Create application.
     */
    @BeforeClass
    public static void setup() {
        SpringApplicationBuilder sb = new SpringApplicationBuilder(Application.class).profiles(SpringProfiles.PROFILE_DEV);
        context = sb.application().run();
        LOGGER.info("Profiles: " + Arrays.toString(context.getEnvironment().getActiveProfiles()));

        LOGGER.info("HTTP Properties LR: " + context.getBean(LivingRoomHttpProperties.class));
        LOGGER.info("HTTP Properties OW: " + context.getBean(OpenWeatherMapHttpProperties.class));
        LOGGER.info("Datasource Properties" + context.getBean(DatabaseProperties.class));

    }

    /**
     * Terminate application.
     */
    @AfterClass
    public static void tearDown() {
        context.close();
    }

    /**
     * Gets the logger.
     *
     * @return the logger
     */
    protected static Logger getLogger() {
        return LOGGER;
    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    protected static ConfigurableApplicationContext getContext() {
        return context;
    }
}
