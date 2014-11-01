package de.beosign.weatherstation.retrieve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.SensorProperties;
import de.beosign.weatherstation.sensor.Sensor;
import de.beosign.weatherstation.sensor.SensorRepository;

/**
 * A receiver that stores the necessary sensors on startup. All receivers shoul extend from this base class.
 * 
 * @author Florian Dahlmanns
 * @param <T> The type of the retrieved data
 */
@Component
public abstract class AbstractRetriever<T> implements Retriever<T>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRetriever.class);

    private SensorProperties sensorProperties;
    private Sensor sensor;

    @Autowired
    private SensorRepository sensorRepository;

    /**
     * Subclasses must return their seonsor properties.
     * 
     * @return properties
     */
    protected abstract SensorProperties getSensorProperties();

    /**
     * Subclasses can do their own initialization here. This method is called at the end of {@link #afterPropertiesSet()}.
     * 
     * @throws Exception on {@link Exception}
     */
    protected void doAfterPropertiesSet() throws Exception {
    }

    /**
     * Save the sensor first because it must exist before saving any reading referencing this sensor.
     * 
     * @throws Exception on {@link Exception}.
     */
    @Override
    public final void afterPropertiesSet() throws Exception {
        sensorProperties = getSensorProperties();

        Sensor transientSensor = new Sensor(sensorProperties.getName(), sensorProperties.getDescription());
        sensor = sensorRepository.findByName(transientSensor.getName());
        if (sensor == null) {
            LOGGER.info("No sensor named {} found, creating sensor", transientSensor.getName());
            sensor = sensorRepository.save(transientSensor);

        } else {
            LOGGER.info("Sensor named {} found, using existing sensor", sensor.getName());
        }

        doAfterPropertiesSet();
    }

    @Override
    public Sensor getSensor() {
        return sensor;
    }
}
