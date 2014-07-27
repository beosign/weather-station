package de.beosign.test.weatherstation.properties;

import org.junit.Test;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.weatherstation.properties.DatabaseProperties;
import de.beosign.weatherstation.properties.HttpProperties;

public class PropertiesTest extends JUnitUtil {

    @Test
    public void testDatabase() {
        DatabaseProperties p = context.getBean(DatabaseProperties.class);
        System.out.println(p.getDriverClassName());
        System.out.println(p.getUrl());
        System.out.println(p.getUsername());
    }

    @Test
    public void testHttp() {
        HttpProperties httpProperties = context.getBean(HttpProperties.class);
        System.out.println(httpProperties.getBaseurl());
        System.out.println(httpProperties.getTemperature().getContext());
        System.out.println(httpProperties.getTemperature().getBasicauth().getUsername());
        System.out.println(httpProperties.getTemperature().getBasicauth().getPassword());
    }
}
