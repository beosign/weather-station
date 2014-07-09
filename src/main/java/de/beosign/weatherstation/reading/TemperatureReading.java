package de.beosign.weatherstation.reading;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureReading extends Reading<Double> {

}
