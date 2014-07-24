package de.beosign.weatherstation.reading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import de.beosign.weatherstation.retrieve.RetrieveException;
import de.beosign.weatherstation.retrieve.TemperatureRetriever;

@Configuration
public class TemperatureOperator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureOperator.class);

    @Autowired
    private TemperatureRetriever temperatureReader;

    @Autowired
    private TemperatureReadingRepository temperatureReadingRepository;

    public void readAndStoreTemperature() {
        Double temp;
        try {
            temp = temperatureReader.retrieve();
            TemperatureReading tr = new TemperatureReading(temp);
            temperatureReadingRepository.save(tr);
        } catch (RetrieveException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
