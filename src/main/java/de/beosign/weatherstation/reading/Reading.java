package de.beosign.weatherstation.reading;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import de.beosign.weatherstation.jpa.JPAEntity;
import de.beosign.weatherstation.sensor.Sensor;

@MappedSuperclass
public abstract class Reading<T> extends JPAEntity {
    private Date readDate = new Date();
    private T value;
    private Sensor sensor;

    protected Reading() {

    }

    public Reading(T value) {
        this(new Date(), value, null);
    }

    public Reading(T value, Sensor sensor) {
        this(new Date(), value, sensor);
    }

    public Reading(Date readDate, T value, Sensor sensor) {
        this.readDate = readDate;
        this.value = value;
        this.sensor = sensor;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Reading [readDate=" + readDate + ", value=" + value + ", sensor=" + sensor + "]";
    }
}
