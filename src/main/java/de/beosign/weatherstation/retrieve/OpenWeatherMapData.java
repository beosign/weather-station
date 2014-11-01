package de.beosign.weatherstation.retrieve;

import java.util.Date;

/**
 * POJO for storing values returned from OpenWeather.
 * 
 * @author Florian Dahlmanns
 */
public class OpenWeatherMapData {

    private Double temperature;
    private Date lastupdate;

    /**
     * Gets the temperature.
     *
     * @return the temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature.
     *
     * @param temperature the new temperature
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the lastupdate.
     *
     * @return the lastupdate
     */
    public Date getLastupdate() {
        return lastupdate;
    }

    /**
     * Sets the lastupdate.
     *
     * @param lastupdate the new lastupdate
     */
    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

}
