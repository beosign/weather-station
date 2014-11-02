package de.beosign.test.weatherstation.retrieve;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.weatherstation.ac.owm.OpenWeatherMapHttpRetriever;
import de.beosign.weatherstation.ac.owm.OpenWeatherMapOperator;
import de.beosign.weatherstation.reading.OpenWeatherMapData;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;

/**
 * Test for OpenWeatherMap.
 * 
 * @author Florian Dahlmanns
 */
public class OpenWeatherMapTest extends JUnitUtil {

    /**
     * Tests if converson from json to java works.
     * 
     * @throws IOException on {@link IOException}
     */
    @Test
    public void convertJsonTest() throws IOException {
        String json = IOUtils.toString(new FileInputStream("src/test/java/de/beosign/test/weatherstation/retrieve/weather.json"));

        OpenWeatherMapData data = OpenWeatherMapData.fromJson(json);

        System.out.println(data);

        // CHECKSTYLE:OFF
        Assert.assertEquals(17.25, data.getTemperature().doubleValue(), 0.5);
        Assert.assertEquals(1414928422, data.getTimestamp().toEpochSecond());
        // CHECKSTYLE:ON

    }

    /**
     * Reads value from OpenWeatherMap.
     */
    @Test
    public void retrieve() {
        OpenWeatherMapHttpRetriever retriever = getContext().getBean(OpenWeatherMapHttpRetriever.class);
        Assert.assertNotNull(retriever);

        OpenWeatherMapData data = retriever.retrieve();
        System.out.println(data);

        Assert.assertNotNull(data);

    }

    /**
     * Reads value from OpenWeatherMap and stores it.
     */
    @Test
    public void operator() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        OpenWeatherMapOperator operator = getContext().getBean(OpenWeatherMapOperator.class);

        operator.retrieveAndStore();

        List<TemperatureReading> temperatureReadings = new ArrayList<>();
        repository.findAll().forEach(temperatureReadings::add);

        temperatureReadings.forEach(System.out::println);
        Assert.assertTrue(temperatureReadings.size() > 1);
    }

}
