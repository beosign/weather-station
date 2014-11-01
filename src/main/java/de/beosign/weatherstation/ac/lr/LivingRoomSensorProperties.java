package de.beosign.weatherstation.ac.lr;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.SensorProperties;

/**
 * Defines sensor properties.
 * 
 * @author Florian Dahlmanns
 */
@Component
@ConfigurationProperties(prefix = "livingroom.sensor")
public class LivingRoomSensorProperties extends SensorProperties {

}
