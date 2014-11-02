package de.beosign.test.weatherstation.log;

import org.junit.Assert;
import org.junit.Test;

import de.beosign.weatherstation.logging.Log;

/**
 * Tests logging functionality.
 * 
 * @author Florian Dahlmanns
 */
public class LoggerTest {

    /**
     * 
     */
    @Test
    public void loggerTest() {
        String loggername = Log.logger().getName();
        Log.logger().debug(Log.logger().getName());

        Assert.assertTrue(loggername.equals(getClass().getName()));
    }

}
