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

    @Override
    public String toString() {
        return "Sensor [name=" + name + ", description=" + description + ", getId()=" + getId() + ", getVersion()=" + getVersion() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
