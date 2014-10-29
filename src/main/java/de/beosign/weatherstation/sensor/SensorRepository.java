package de.beosign.weatherstation.sensor;

import org.springframework.data.repository.CrudRepository;

/**
 * CRUD repository for {@link Sensor}s
 * 
 * @author Florian Dahlmanns
 */
public interface SensorRepository extends CrudRepository<Sensor, Long> {
    /**
     * Get sensor by its name (unique).
     * 
     * @param name name
     * @return sensor or null of nothing found
     */
    Sensor findByName(String name);
}
