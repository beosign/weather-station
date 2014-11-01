package de.beosign.weatherstation.ac.lr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.operator.TemperatureOperator;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.retrieve.Retriever;

/**
 * Reads and the stores the temperature in the living room.
 * 
 * @author Florian Dahlmanns
 */
@EnableScheduling
@Component
public class LivingRoomTemperatureOperator extends TemperatureOperator {
    @Autowired
    private LivingRoomHttpTemperatureRetriever temperatureRetriever;

    @Override
    public Retriever<TemperatureReading> getRetriever() {
        return temperatureRetriever;
    }

    @Override
    @Scheduled(fixedDelayString = "${livingroom.http.temperature.query_interval}")
    protected void readAndStoreScheduled() {
        retrieveAndStore();

    }

}
