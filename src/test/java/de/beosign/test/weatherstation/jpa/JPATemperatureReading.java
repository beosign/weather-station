package de.beosign.test.weatherstation.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.test.weatherstation.common.TemperatureUtil;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;
import de.beosign.weatherstation.sensor.Sensor;
import de.beosign.weatherstation.sensor.SensorRepository;

/**
 * Test JPA.
 * 
 * @author Florian Dahlmanns
 */
public class JPATemperatureReading extends JUnitUtil {

    @Test
    public void saveTemperatureReading() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(tr.getSensor());
        repository.save(tr);

    }

    @Test
    public void saveTwoReadingsForSameSensorTest() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        Sensor s = new Sensor("sensor", "asd");
        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(s);
        tr.setSensor(s);
        repository.save(tr);

        TemperatureReading tr2 = TemperatureUtil.createTemperatureReading();
        // s = sensorRepository.findByName(tr.getSensor().getName());
        tr2.setSensor(s);
        repository.save(tr2);

        List<TemperatureReading> temperatureReadings = new ArrayList<>();
        repository.findAll().forEach(temperatureReadings::add);

        Assert.assertTrue("Two readings", repository.count() == 2);
        Assert.assertTrue("Only one sensor", temperatureReadings.get(0).getSensor().getId() == temperatureReadings.get(1).getSensor().getId());

        System.out.println(temperatureReadings);

    }

    @Test
    public void getReadingBetween() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(tr.getSensor());
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 1), tr.getSensor());
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 2), tr.getSensor());
        repository.save(tr);

        List<TemperatureReading> tempReads = repository.findByReadDateBetween(new Date((long) (System.currentTimeMillis() - 1000 * 3600 * 24 * 1.5)), new Date(
                System.currentTimeMillis() + 1000 * 1000));

        tempReads.forEach(System.out::println);
        Assert.assertTrue("Read all failed", tempReads != null);
        Assert.assertTrue("Size must be > 0", tempReads.size() > 0);

    }

}
