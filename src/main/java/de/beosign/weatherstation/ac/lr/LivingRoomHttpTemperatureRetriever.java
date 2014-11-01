package de.beosign.weatherstation.ac.lr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.HttpProperties;
import de.beosign.weatherstation.retrieve.HttpTemperatureRetriever;

/**
 * Retrieves temperature in living room.
 * 
 * @author Florian Dahlmanns
 */
@Component
public class LivingRoomHttpTemperatureRetriever extends HttpTemperatureRetriever {

    @Autowired
    private LivingRoomHttpProperties livingRoomHttpProperties;

    @Override
    protected HttpProperties getHttpProperties() {

        return livingRoomHttpProperties;
    }

}
