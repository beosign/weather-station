package de.beosign.test.weatherstation.common;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import de.beosign.test.weatherstation.rest.RestReading;
import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.spring.SpringProfiles;

/**
 * Sets up Spring {@link ApplicationContext} and a logger.
 * 
 * @author florian
 */
public class JUnitUtil {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RestReading.class);
    protected static ConfigurableApplicationContext context;

    @BeforeClass
    public static void setup() {
        SpringApplicationBuilder sb = new SpringApplicationBuilder(Application.class).profiles(SpringProfiles.PROFILE_DEV);
        context = sb.application().run();
        LOGGER.info("Profiles: " + Arrays.toString(context.getEnvironment().getActiveProfiles()));

    }

    @AfterClass
    public static void tearDown() {
        context.close();
    }
}
