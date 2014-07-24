package de.beosign.test.weatherstation.read;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.reading.TemperatureOperator;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;

public class TemperatureOperatorHTTP {
    private static ConfigurableApplicationContext context;
    private static TemperatureOperator temperatureOperator;

    @BeforeClass
    public static void setup() {
        context = SpringApplication.run(Application.class);
        temperatureOperator = context.getBean(TemperatureOperator.class);

    }

    @AfterClass
    public static void tearDown() {
        context.close();
    }

    @Test
    public void readTempHttp() throws KeyManagementException, NoSuchAlgorithmException, IOException {
        Date fromDate = new Date(new Date().getTime() - 10000);
        Assert.assertNotNull(temperatureOperator);

        temperatureOperator.readAndStoreTemperature();

        TemperatureReadingRepository repository = context.getBean(TemperatureReadingRepository.class);

        List<TemperatureReading> temperatureReadings = repository.findByReadDateBetween(fromDate, new Date());

        Assert.assertTrue(temperatureReadings.size() > 0);

        System.out.println("Temperatures found: " + temperatureReadings.size());
        temperatureReadings.forEach(System.out::println);

    }
}
