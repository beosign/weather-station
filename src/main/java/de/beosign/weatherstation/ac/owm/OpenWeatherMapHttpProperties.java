package de.beosign.weatherstation.ac.owm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.HttpProperties;

/**
 * Defines prefix for HttpProperties.
 * 
 * @author Florian Dahlmanns
 */
@Component
@ConfigurationProperties(prefix = "aachen.owm.http")
public class OpenWeatherMapHttpProperties extends HttpProperties {

}
