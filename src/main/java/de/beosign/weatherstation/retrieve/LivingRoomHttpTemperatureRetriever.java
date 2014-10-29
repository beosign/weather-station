package de.beosign.weatherstation.retrieve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.HttpProperties;
import de.beosign.weatherstation.properties.LivingRoomHttpProperties;

@Component
public class LivingRoomHttpTemperatureRetriever extends HttpTemperatureRetriever {

    @Autowired
    private LivingRoomHttpProperties livingRoomHttpProperties;

    @Override
    protected HttpProperties getHttpProperties() {

        return livingRoomHttpProperties;
    }

}
