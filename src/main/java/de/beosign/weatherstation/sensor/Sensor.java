package de.beosign.weatherstation.sensor;

import javax.persistence.Entity;

import de.beosign.weatherstation.jpa.JPAEntity;

@Entity
public class Sensor extends JPAEntity {
    private String name;
    private String description;

    protected Sensor() {

    }

    public Sensor(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
