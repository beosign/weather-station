package de.beosign.weatherstation.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;
import de.beosign.weatherstation.retrieve.TemperatureRetriever;
import de.beosign.weatherstation.sensor.Sensor;
import de.beosign.weatherstation.sensor.SensorRepository;

@EnableScheduling
public abstract class TemperatureOperator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureOperator.class);
    private Sensor sensor;

    @Autowired
    private TemperatureReadingRepository temperatureReadingRepository;

    @Autowired
    private SensorRepository sensorRepository;

    protected abstract TemperatureRetriever getTemperatureRetriever();

    protected abstract Sensor getTemperatureSensor();

    protected abstract void readAndStoreScheduled();

    public void readAndStoreTemperature() {
        Double temp;
        temp = getTemperatureRetriever().retrieve();

        if (sensor == null) {
            sensor = sensorRepository.save(getTemperatureSensor());
        }

        TemperatureReading tr = null;
        tr = new TemperatureReading(temp, sensor);
        temperatureReadingRepository.save(tr);

    }

    @Scheduled(fixedDelayString = "${livingroom.http.temperature.query_interval}")
    protected void readTemperatures() {
        temperatureReadingRepository.findAll().forEach(s -> LOGGER.debug("DEBUG-READING: " + s.toString()));

    }

}
