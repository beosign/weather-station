package de.beosign.weatherstation.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;
import de.beosign.weatherstation.retrieve.TemperatureRetriever;
import de.beosign.weatherstation.sensor.Sensor;
import de.beosign.weatherstation.sensor.SensorRepository;

/**
 * All Temperature Operators (classes that read a temperature and store the result) should extend this class.
 * 
 * @author Florian Dahlmanns
 */
@EnableScheduling
public abstract class TemperatureOperator implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureOperator.class);
    private Sensor sensor;

    @Autowired
    private TemperatureReadingRepository temperatureReadingRepository;

    @Autowired
    private SensorRepository sensorRepository;

    /**
     * Save the sensor first because it must exist before saving any reading referencing this sensor.
     * 
     * @throws Exception on {@link Exception}.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Sensor transientSensor = getTemperatureSensor();
        sensor = sensorRepository.findByName(transientSensor.getName());
        if (sensor == null) {
            LOGGER.info("No sensor named {} found, creating sensor", transientSensor.getName());
            sensor = sensorRepository.save(transientSensor);
        } else {
            LOGGER.info("Sensor named {} found, using existing sensor", sensor.getName());
        }
    }

    /**
     * Returns the concrete {@link TemperatureRetriever} instance used to retrieve the temperature.
     * 
     * @return the concrete {@link TemperatureRetriever} instance
     */
    protected abstract TemperatureRetriever getTemperatureRetriever();

    /**
     * Returns the sensor where the temperature is read from.
     * 
     * @return sensor
     */
    protected abstract Sensor getTemperatureSensor();

    /**
     * Classes must implement this method and annotate it with {@link Open Declaration org.springframework.scheduling.annotation.Scheduled} such that this
     * method is called regularly. They must simply call {@link #readAndStoreTemperature()}.
     * This is a workaround to be able to provide a variable schdeule expression read from a property file as an annotation value must have a constant
     * expression.
     */
    protected abstract void readAndStoreScheduled();

    /**
     * Called by subclasses in {@link #readAndStoreScheduled()}. Reads temperature and stores result.
     */
    public void readAndStoreTemperature() {
        Double temp = getTemperatureRetriever().retrieve();
        TemperatureReading tr = new TemperatureReading(temp, sensor);

        temperatureReadingRepository.save(tr);
        LOGGER.info("Stored temperature: " + tr);
    }

    // @Scheduled(fixedDelayString = "${livingroom.http.temperature.query_interval}")
    // protected void readTemperatures() {
    // temperatureReadingRepository.findAll().forEach(s -> LOGGER.debug("DEBUG-READING: " + s.toString()));
    //
    // }

}
