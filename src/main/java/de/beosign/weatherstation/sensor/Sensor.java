package de.beosign.weatherstation.sensor;

import javax.persistence.Entity;

import de.beosign.weatherstation.jpa.JPAEntity;

/**
 * A sensor that reads values from its environment, like temperature.
 * 
 * @author Florian Dahlmanns
 */
@Entity
public class Sensor extends JPAEntity {
    private String name;
    private String description;

    /**
     * Instantiates a new sensor. Protected such that this class could be overridden. A no-arg constructor must be present for JPA.
     */
    protected Sensor() {
    }

    /**
     * Instantiates a new sensor.
     *
     * @param name the name
     * @param description the description
     */
    public Sensor(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
