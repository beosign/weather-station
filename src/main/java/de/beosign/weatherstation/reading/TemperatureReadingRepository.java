package de.beosign.weatherstation.reading;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Rest and JPA repository.
 * 
 * @author florian
 */
@RepositoryRestResource(path = "temperaturereadings")
public interface TemperatureReadingRepository extends ReadingRepository<TemperatureReading> {

}
