package de.beosign.weatherstation.reading;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
        super(temp);
    }

}
