package de.beosign.test.weatherstation.read;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.reading.HttpTemperatureRetriever;

public class RetrieveTemperatureHTTP {
    private static ConfigurableApplicationContext context;
    private static HttpTemperatureRetriever httpTemperatureReader;

    @BeforeClass
    public static void setup() {
        context = SpringApplication.run(Application.class);
        httpTemperatureReader = context.getBean(HttpTemperatureRetriever.class);

    }

    @AfterClass
    public static void tearDown() {
        context.close();
    }

    @Test
    public void readTempHttp() throws KeyManagementException, NoSuchAlgorithmException, IOException {
        Assert.assertNotNull(httpTemperatureReader);

        System.out.println(httpTemperatureReader.retrieveTemperature());

    }
}
