package de.beosign.test.weatherstation.properties;

import org.junit.Assert;
import org.junit.Test;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.weatherstation.ac.lr.LivingRoomHttpProperties;
import de.beosign.weatherstation.ac.owm.OpenWeatherMapHttpProperties;
import de.beosign.weatherstation.properties.DatabaseProperties;
import de.beosign.weatherstation.properties.HttpProperties;

/**
 * Tests property classes.
 * 
 * @author Florian Dahlmanns
 */
public class PropertiesTest extends JUnitUtil {

    /**
     * Test if database settings can be read.
     */
    @Test
    public void testDatabase() {
        DatabaseProperties p = getContext().getBean(DatabaseProperties.class);
        System.out.println(p);

        Assert.assertNotNull(p);

    }

    /**
     * Test if http living room settings can be read.
     */
    @Test
    public void testHttpLivingRoom() {
        HttpProperties lrHttpProperties = getContext().getBean(LivingRoomHttpProperties.class);

        System.out.println(lrHttpProperties);

        Assert.assertNotNull(lrHttpProperties);

    }

    /**
     * Test if http openweathermap settings can be read.
     */
    @Test
    public void testHttOpenWeather() {
        HttpProperties owmHttpProperties = getContext().getBean(OpenWeatherMapHttpProperties.class);

        System.out.println(owmHttpProperties);

        Assert.assertNotNull(owmHttpProperties);

    }
}
