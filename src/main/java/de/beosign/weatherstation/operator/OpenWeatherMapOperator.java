package de.beosign.weatherstation.operator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;
import de.beosign.weatherstation.retrieve.OpenWeatherMapData;
import de.beosign.weatherstation.retrieve.OpenWeatherMapHttpRetriever;
import de.beosign.weatherstation.retrieve.Retriever;

/**
 * Reads and the stores the temperature from <a href="http://www.OpenWeatherMap.org">OpenWeatherMap</a>.
 * 
 * @author Florian Dahlmanns
 */
@EnableScheduling
@Component
public class OpenWeatherMapOperator implements Operator<OpenWeatherMapData> {
    private static final Logger LOGGER = Logger.getLogger(OpenWeatherMapOperator.class);

    @Autowired
    private OpenWeatherMapHttpRetriever httpRetriever;

    @Autowired
    private TemperatureReadingRepository temperatureReadingRepository;

    @Override
    public Retriever<OpenWeatherMapData> getRetriever() {
        return httpRetriever;
    }

    @Scheduled(fixedDelayString = "${aachen.owm.http.temperature.query_interval}")
    @Override
    public void retrieveAndStore() {
        OpenWeatherMapData data = getRetriever().retrieve();
        TemperatureReading tr = new TemperatureReading(data.getLastupdate(), data.getTemperature(), httpRetriever.getSensor());

        temperatureReadingRepository.save(tr);
        LOGGER.info("Stored temperature: " + tr);

    }

}
