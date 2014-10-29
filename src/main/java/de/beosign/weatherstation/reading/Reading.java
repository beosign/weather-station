package de.beosign.weatherstation.reading;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.repository.CrudRepository;

import de.beosign.weatherstation.jpa.JPAEntity;
import de.beosign.weatherstation.sensor.Sensor;

/**
 * Entity representing a reading, i.e. a value read from a sensor at some point in time.
 * 
 * @author Florian Dahlmanns
 * @param <T> the type of the value being read
 */
@MappedSuperclass
public abstract class Reading<T> extends JPAEntity {
    private Date readDate = new Date();
    private T value;
    private Sensor sensor;

    /**
     * Instantiates a new reading.
     */
    protected Reading() {
    }

    /**
     * Instantiates a new reading.
     *
     * @param value the value
     */
    public Reading(T value) {
        this(new Date(), value, null);
    }

    /**
     * Instantiates a new reading.
     *
     * @param value the value
     * @param sensor the sensor
     */
    public Reading(T value, Sensor sensor) {
        this(new Date(), value, sensor);
    }

    /**
     * Instantiates a new reading.
     *
     * @param readDate the read date
     * @param value the value
     * @param sensor the sensor
     */
    public Reading(Date readDate, T value, Sensor sensor) {
        this.readDate = readDate;
        this.value = value;
        this.sensor = sensor;
    }

    /**
     * Gets the read date.
     *
     * @return the read date
     */
    public Date getReadDate() {
        return readDate;
    }

    /**
     * Sets the read date.
     *
     * @param readDate the new read date
     */
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Gets the sensor. There is no {@link CascadeType} defined here as this made it impossible to use the simple {@link CrudRepository} for storing a
     * temperature reading when the sensor already existed (error: detached entity cannot be persisted)
     *
     * @return the sensor
     */
    @ManyToOne
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * Sets the sensor.
     *
     * @param sensor the new sensor
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Reading [readDate=" + readDate + ", value=" + value + ", sensor=" + sensor + "]";
    }
}
