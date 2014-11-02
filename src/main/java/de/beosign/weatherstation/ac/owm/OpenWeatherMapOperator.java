package de.beosign.weatherstation.ac.owm;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.logging.Log;
import de.beosign.weatherstation.operator.Operator;
import de.beosign.weatherstation.reading.OpenWeatherMapData;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;
import de.beosign.weatherstation.retrieve.Retriever;

/**
 * Reads and the stores the temperature from <a href="http://www.OpenWeatherMap.org">OpenWeatherMap</a>.
 * 
 * @author Florian Dahlmanns
 */
@EnableScheduling
@Component
public class OpenWeatherMapOperator implements Operator<OpenWeatherMapData> {
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
        TemperatureReading tr = new TemperatureReading(Date.from(data.getTimestamp().toInstant()), data.getTemperature(), httpRetriever.getSensor());

        temperatureReadingRepository.save(tr);
        Log.logger().info("Stored temperature: " + tr);

    }

}
