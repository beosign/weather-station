package de.beosign.weatherstation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "livingroom.http")
public class LivingRoomHttpProperties extends HttpProperties {

}
