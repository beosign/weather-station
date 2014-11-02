package de.beosign.test.weatherstation.retrieve;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.weatherstation.ac.lr.LivingRoomHttpTemperatureRetriever;
import de.beosign.weatherstation.ac.lr.LivingRoomTemperatureOperator;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;

/**
 * Test cases for living room sensor.
 * 
 * @author Florian Dahlmanns
 */
public class LivingRoomTemperatureTest extends JUnitUtil {

    /**
     * Reads value from living room sensor.
     */
    @Test
    public void retrieve() {
        LivingRoomHttpTemperatureRetriever retriever = getContext().getBean(LivingRoomHttpTemperatureRetriever.class);
        Assert.assertNotNull(retriever);

        TemperatureReading tr = retriever.retrieve();
        System.out.println(tr);

        Assert.assertNotNull(tr);

    }

    /**
     * Reads value from sensor and stores it in database.
     */
    @Test
    public void operator() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        LivingRoomTemperatureOperator operator = getContext().getBean(LivingRoomTemperatureOperator.class);

        operator.retrieveAndStore();

        List<TemperatureReading> temperatureReadings = new ArrayList<>();
        repository.findAll().forEach(temperatureReadings::add);

        temperatureReadings.forEach(System.out::println);
        Assert.assertTrue(temperatureReadings.size() > 1);
    }
}
