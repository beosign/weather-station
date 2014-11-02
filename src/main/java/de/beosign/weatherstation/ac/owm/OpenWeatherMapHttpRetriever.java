package de.beosign.weatherstation.ac.owm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.logging.Log;
import de.beosign.weatherstation.properties.HttpProperties;
import de.beosign.weatherstation.properties.SensorProperties;
import de.beosign.weatherstation.reading.OpenWeatherMapData;
import de.beosign.weatherstation.retrieve.HttpRetriever;

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
    protected OpenWeatherMapData extract(InputStream inputStream) throws IOException {
        String json = IOUtils.toString(inputStream);
        Log.logger().debug("JSON from OpenWeatherMap: " + json);
        return OpenWeatherMapData.fromJson(json);
    }

    @Override
    protected SensorProperties getSensorProperties() {
        return openWeatherMapSensorProperties;
    }

}
