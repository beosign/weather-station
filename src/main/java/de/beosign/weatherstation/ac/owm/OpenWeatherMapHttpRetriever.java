package de.beosign.weatherstation.ac.owm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapHttpRetriever.class);

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
        LOGGER.debug("JSON from OpenWeatherMap: " + json);
        return OpenWeatherMapData.fromJson(json);
    }

    @Override
    protected SensorProperties getSensorProperties() {
        return openWeatherMapSensorProperties;
    }

}
