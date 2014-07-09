package de.beosign.weatherstation.reading;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Rest and JPA repository.
 * 
 * @author florian
 */
@RepositoryRestResource(path = "temperaturereadings")
public interface TemperatureReadingRepository extends ReadingRepository<TemperatureReading> {
    List<TemperatureReading> findByReadDateBetween(@Param("fromDate") @DateTimeFormat(iso = ISO.DATE) Date fromDate, @Param("toDate") @DateTimeFormat(
            iso = ISO.DATE) Date toDate);
}
