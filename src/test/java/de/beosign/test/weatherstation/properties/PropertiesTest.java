package de.beosign.test.weatherstation.properties;

import org.junit.Assert;
import org.junit.Test;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.weatherstation.ac.lr.LivingRoomHttpProperties;
import de.beosign.weatherstation.properties.DatabaseProperties;
import de.beosign.weatherstation.properties.HttpProperties;

public class PropertiesTest extends JUnitUtil {

    @Test
    public void testDatabase() {
        DatabaseProperties p = context.getBean(DatabaseProperties.class);
        System.out.println(p);

        Assert.assertNotNull(p);

    }

    @Test
    public void testHttpLR() {
        HttpProperties httpProperties = context.getBean(LivingRoomHttpProperties.class);
        System.out.println(httpProperties);

        Assert.assertNotNull(httpProperties);

    }
}
