package de.beosign.test.weatherstation.read;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.weatherstation.operator.TemperatureOperator;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;

public class TemperatureOperatorHTTP extends JUnitUtil {
    private TemperatureOperator temperatureOperator;

    public void readTempHttp() throws KeyManagementException, NoSuchAlgorithmException, IOException {
        temperatureOperator = context.getBean(TemperatureOperator.class);
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
