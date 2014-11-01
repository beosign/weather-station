package de.beosign.weatherstation.ac.lr;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.HttpProperties;

/**
 * Defines prefix for HttpProperties.
 * 
 * @author Florian Dahlmanns
 */
@Component
@ConfigurationProperties(prefix = "livingroom.http")
public class LivingRoomHttpProperties extends HttpProperties {

}
