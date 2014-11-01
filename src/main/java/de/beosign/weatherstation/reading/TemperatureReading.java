package de.beosign.weatherstation.reading;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.beosign.weatherstation.sensor.Sensor;

/**
 * Temperature reading entity. This class is annotated with {@code JsonIgnoreProperties=true} because the rest test methods return fields like {@code _links}
 * that are common in HATEOAS.
 * 
 * @author Florian Dahlmanns
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureReading extends Reading<Double> {

    /**
     * Instantiates a new temperature reading. A no-arg Constructor must be present for JPA.
     */
    protected TemperatureReading() {

    }

    /**
     * Instantiates a new temperature reading.
     *
     * @param temp the temp
     */
    public TemperatureReading(Double temp) {
        this(temp, null);
    }

    /**
     * Instantiates a new temperature reading.
     *
     * @param temp the temp
     * @param sensor sensor
     */
    public TemperatureReading(Double temp, Sensor sensor) {
        super(temp, sensor);
    }

    /**
     * Instantiates a new temperature reading.
     *
     * @param date when has the value been read
     * @param temp the temperature
     * @param sensor sensor
     */
    public TemperatureReading(Date date, Double temp, Sensor sensor) {
        super(date, temp, sensor);
    }

}
