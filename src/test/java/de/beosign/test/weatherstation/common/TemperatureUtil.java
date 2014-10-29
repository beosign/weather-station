package de.beosign.test.weatherstation.common;

import java.util.Date;

import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.sensor.Sensor;

public final class TemperatureUtil {
    private TemperatureUtil() {
    }

    public static TemperatureReading createTemperatureReading() {
        // save a couple of customers
        Sensor s = new Sensor("LR-1", "Living Room 1");

        TemperatureReading tr = new TemperatureReading(13.5, s);

        return tr;
    }

    public static TemperatureReading createTemperatureReading(Date date) {
        // save a couple of customers
        Sensor s = new Sensor("LR-1", "Living Room 1");

        TemperatureReading tr = new TemperatureReading(13.5);
        tr.setReadDate(date);
        tr.setSensor(s);

        return tr;
    }

    public static TemperatureReading createTemperatureReading(Date date, Sensor sensor) {
        TemperatureReading tr = new TemperatureReading(13.5, sensor);
        tr.setReadDate(date);

        return tr;
    }
}
