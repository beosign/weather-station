package de.beosign.weatherstation.operator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.LivingRoomSensorProperties;
import de.beosign.weatherstation.retrieve.LivingRoomHttpTemperatureRetriever;
import de.beosign.weatherstation.retrieve.TemperatureRetriever;
import de.beosign.weatherstation.sensor.Sensor;

/**
 * Reads and the stores the temperature in the living room.
 * 
 * @author Florian Dahlmanns
 */
@EnableScheduling
@Component
public class LivingRoomTemperatureOperator extends TemperatureOperator {
    @Autowired
    private LivingRoomHttpTemperatureRetriever temperatureReader;

    @Autowired
    private LivingRoomSensorProperties sensorProperties;

    @Override
    protected TemperatureRetriever getTemperatureRetriever() {
        return temperatureReader;
    }

    @Scheduled(fixedDelayString = "${livingroom.http.temperature.query_interval}")
    @Override
    protected void readAndStoreScheduled() {
        readAndStoreTemperature();

    }

    @Override
    protected Sensor getTemperatureSensor() {
        return new Sensor(sensorProperties.getName(), sensorProperties.getDescription());
    }

}
