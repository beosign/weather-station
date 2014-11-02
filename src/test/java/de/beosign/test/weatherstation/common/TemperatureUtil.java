package de.beosign.test.weatherstation.common;

import java.util.Date;

import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.sensor.Sensor;

/**
 * The Class TemperatureUtil.
 */
public final class TemperatureUtil {

    /**
     * Instantiates a new temperature util.
     */
    private TemperatureUtil() {
    }

    /**
     * Creates the temperature reading.
     *
     * @return the temperature reading
     */
    public static TemperatureReading createTemperatureReading() {
        Sensor s = new Sensor("LR-1", "Living Room 1");
        TemperatureReading tr = new TemperatureReading(4.0, s);

        return tr;
    }

    /**
     * Creates the temperature reading.
     *
     * @param date the date
     * @return the temperature reading
     */
    public static TemperatureReading createTemperatureReading(Date date) {
        Sensor s = new Sensor("LR-1", "Living Room 1");

        TemperatureReading tr = new TemperatureReading(4.0);
        tr.setReadDate(date);
        tr.setSensor(s);

        return tr;
    }

    /**
     * Creates the temperature reading.
     *
     * @param date the date
     * @param sensor the sensor
     * @return the temperature reading
     */
    public static TemperatureReading createTemperatureReading(Date date, Sensor sensor) {
        TemperatureReading tr = new TemperatureReading(4.0, sensor);
        tr.setReadDate(date);

        return tr;
    }
}
