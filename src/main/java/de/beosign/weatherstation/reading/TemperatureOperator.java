package de.beosign.weatherstation.reading;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemperatureOperator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureOperator.class);

    @Autowired
    private HttpTemperatureRetriever temperatureReader;

    @Autowired
    private TemperatureReadingRepository temperatureReadingRepository;

    public void readAndStoreTemperature() {
        Double temp;
        try {
            temp = temperatureReader.retrieveTemperature();
            TemperatureReading tr = new TemperatureReading(temp);
            temperatureReadingRepository.save(tr);
        } catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
