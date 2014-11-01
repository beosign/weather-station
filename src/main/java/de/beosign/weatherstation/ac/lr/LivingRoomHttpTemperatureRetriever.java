package de.beosign.weatherstation.ac.lr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.HttpProperties;
import de.beosign.weatherstation.properties.SensorProperties;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.retrieve.HttpRetriever;
import de.beosign.weatherstation.retrieve.RetrieveException;

/**
 * Retrieves temperature in living room.
 * 
 * @author Florian Dahlmanns
 */
@Component
public class LivingRoomHttpTemperatureRetriever extends HttpRetriever<TemperatureReading> {

    @Autowired
    private LivingRoomHttpProperties livingRoomHttpProperties;

    @Autowired
    private LivingRoomSensorProperties livingRoomSensorProperties;

    @Override
    protected TemperatureReading extract(InputStream inputStream) {
        String strTemp;
        try {
            strTemp = IOUtils.toString(inputStream);
            return new TemperatureReading(new Date(), Double.valueOf(strTemp), getSensor());
        } catch (IOException e) {
            throw new RetrieveException("Cannot extract temperature from input stream: " + e.getMessage(), e);
        }
    }

    @Override
    protected HttpProperties getHttpProperties() {

        return livingRoomHttpProperties;
    }

    @Override
    protected SensorProperties getSensorProperties() {
        return livingRoomSensorProperties;
    }

}
