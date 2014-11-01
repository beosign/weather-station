package de.beosign.weatherstation.ac.owm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.SensorProperties;

/**
 * Defines sensor properties.
 * 
 * @author Florian Dahlmanns
 */
@Component
@ConfigurationProperties(prefix = "aachen.owm.sensor")
public class OpenWeatherMapSensorProperties extends SensorProperties {

}
