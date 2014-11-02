package de.beosign.weatherstation.reading;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.beosign.weatherstation.sensor.Sensor;

/**
 * Base interface for {@link Reading} repositories. Annotated with {@link NoRepositoryBean} as this interface does not define a concrete repository.
 * 
 * @author Florian Dahlmanns
 * @param <S> Concrete {@link Reading} type, like {@link TemperatureReading}.
 */
@NoRepositoryBean
public interface ReadingRepository<S extends Reading<?>> extends CrudRepository<S, Long> {
    /**
     * Returns all readings within a given date interval.
     * 
     * @param fromDate start date
     * @param toDate end date
     * @return all readings within the interval.
     */
    List<S> findByReadDateBetween(@Param("fromDate") @DateTimeFormat(iso = ISO.DATE) Date fromDate, @Param("toDate") @DateTimeFormat(iso = ISO.DATE) Date toDate);

    /**
     * Returns all readings for a given sensor within a given date interval.
     * 
     * @param sensor sensor
     * @param fromDate start date
     * @param toDate end date
     * @return all readings for given sensor within the interval.
     */
    List<S> findBySensorAndReadDateBetween(@Param("sensorid") Sensor sensor, @Param("fromDate") @DateTimeFormat(iso = ISO.DATE) Date fromDate,
            @Param("toDate") @DateTimeFormat(iso = ISO.DATE) Date toDate);

    /**
     * Returns all readings for a given sensor.
     * 
     * @param sensor sensor
     * @return all readings for given sensor
     */
    List<S> findBySensor(@Param("sensorid") Sensor sensor);

}
