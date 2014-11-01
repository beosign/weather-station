package de.beosign.weatherstation.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;

/**
 * All Temperature Operators (classes that read a temperature and store the result) should extend this class.
 * 
 * @author Florian Dahlmanns
 */
public abstract class TemperatureOperator implements Operator<TemperatureReading> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureOperator.class);

    @Autowired
    private TemperatureReadingRepository temperatureReadingRepository;

    /**
     * Classes must implement this method and annotate it with {@link org.springframework.scheduling.annotation.Scheduled} such that this
     * method is called regularly. They must simply call {@link #retrieveAndStore()}.
     * This is a workaround to be able to provide a variable schedule expression read from a property file as an annotation value must have a constant
     * expression.
     */
    protected abstract void readAndStoreScheduled();

    /**
     * Called by subclasses in {@link #readAndStoreScheduled()}. Reads temperature and stores result.
     */
    @Override
    public void retrieveAndStore() {
        TemperatureReading tr = getRetriever().retrieve();

        temperatureReadingRepository.save(tr);
        LOGGER.info("Stored temperature: " + tr);
    }

    // @Scheduled(fixedDelayString = "${livingroom.http.temperature.query_interval}")
    // protected void readTemperatures() {
    // temperatureReadingRepository.findAll().forEach(s -> LOGGER.debug("DEBUG-READING: " + s.toString()));
    //
    // }

}
