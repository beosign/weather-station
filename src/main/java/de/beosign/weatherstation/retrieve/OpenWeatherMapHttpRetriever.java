package de.beosign.weatherstation.retrieve;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.ac.owm.OpenWeatherMapHttpProperties;
import de.beosign.weatherstation.ac.owm.OpenWeatherMapSensorProperties;
import de.beosign.weatherstation.properties.HttpProperties;
import de.beosign.weatherstation.properties.SensorProperties;

/**
 * Retrieves temperature in living room.
 * 
 * @author Florian Dahlmanns
 */
@Component
public class OpenWeatherMapHttpRetriever extends HttpRetriever<OpenWeatherMapData> {

    @Autowired
    private OpenWeatherMapHttpProperties openWeatherMapHttpProperties;

    @Autowired
    private OpenWeatherMapSensorProperties openWeatherMapSensorProperties;

    @Override
    protected HttpProperties getHttpProperties() {

        return openWeatherMapHttpProperties;
    }

    @Override
    protected OpenWeatherMapData extract(InputStream inputStream) {
        OpenWeatherMapData data = new OpenWeatherMapData();
        data.setLastupdate(new Date());
        data.setTemperature(2.0);

        // TODO Parse
        return data;
    }

    @Override
    protected SensorProperties getSensorProperties() {
        return openWeatherMapSensorProperties;
    }

}
