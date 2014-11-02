package de.beosign.test.weatherstation.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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

    /**
     * Deletes temperature reading repository.
     */
    @Before
    public void before() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        repository.deleteAll();
    }

    /**
     * Tests method {@link de.beosign.weatherstation.reading.ReadingRepository#save(Object)}.
     */
    @Test
    public void saveTemperatureReading() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(tr.getSensor());
        repository.save(tr);

    }

    /**
     * Tests method {@link de.beosign.weatherstation.reading.ReadingRepository#save(Object)} for two readings for the same sensor.
     */
    @Test
    public void saveTwoReadingsForSameSensorTest() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        Sensor s1 = new Sensor("sensor", "asd");
        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(s1);
        tr.setSensor(s1);
        repository.save(tr);

        TemperatureReading tr2 = TemperatureUtil.createTemperatureReading();
        // s = sensorRepository.findByName(tr.getSensor().getName());
        tr2.setSensor(s1);
        repository.save(tr2);

        List<TemperatureReading> temperatureReadings = new ArrayList<>();
        repository.findAll().forEach(temperatureReadings::add);

        Assert.assertTrue("Two readings", repository.count() == 2);
        Assert.assertTrue("Only one sensor", temperatureReadings.get(0).getSensor().getId() == temperatureReadings.get(1).getSensor().getId());

        System.out.println(temperatureReadings);
    }

    /**
     * Tests method {@link de.beosign.weatherstation.reading.ReadingRepository#findByReadDateBetween(Date, Date)}.
     */
    @Test
    public void getReadingBetween() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(tr.getSensor());
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 1), tr.getSensor());
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 3), tr.getSensor());
        repository.save(tr);

        List<TemperatureReading> tempReads = repository.findByReadDateBetween(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 2),
                new Date(System.currentTimeMillis() + 1000 * 1000));

        tempReads.forEach(System.out::println);
        Assert.assertTrue("Read all failed", tempReads != null);
        Assert.assertTrue("Size must be = 2", tempReads.size() == 2);

    }

    /**
     * Tests method {@link de.beosign.weatherstation.reading.ReadingRepository#findBySensorAndReadDateBetween(Sensor, Date, Date)}.
     */
    @Test
    public void getReadingBetweenAndSensor() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        // Create readings for a sensor
        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(tr.getSensor());
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 1), tr.getSensor());
        repository.save(tr);

        // Create reading for a different sensor, only this should be returned
        Sensor s2 = new Sensor("sensor", "asd");
        sensorRepository.save(s2);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 1), tr.getSensor());
        tr.setSensor(s2);
        repository.save(tr);

        Date fourtyEightHoursPast = new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 2);
        List<TemperatureReading> tempReads = repository.findBySensorAndReadDateBetween(s2, fourtyEightHoursPast, new Date(
                System.currentTimeMillis() + 1000 * 1000));

        tempReads.forEach(System.out::println);
        Assert.assertTrue("Read all failed", tempReads != null);
        Assert.assertTrue("Size must be = 1", tempReads.size() == 1);

    }

    /**
     * Tests method {@link de.beosign.weatherstation.reading.ReadingRepository#findBySensor(Sensor)}.
     */
    @Test
    public void getReadingsBySensor() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        // Create readings for a sensor
        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(tr.getSensor());
        Sensor sensor = tr.getSensor();
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(), tr.getSensor());
        repository.save(tr);

        // Create reading for a different sensor, only this should be returned
        Sensor s2 = new Sensor("sensor", "asd");
        sensorRepository.save(s2);
        tr = TemperatureUtil.createTemperatureReading(new Date(), tr.getSensor());
        tr.setSensor(s2);
        repository.save(tr);

        List<TemperatureReading> tempReads = repository.findBySensor(sensor);

        tempReads.forEach(System.out::println);
        Assert.assertTrue("Read all failed", tempReads != null);
        Assert.assertTrue("Size must be = 2", tempReads.size() == 2);

    }

}
