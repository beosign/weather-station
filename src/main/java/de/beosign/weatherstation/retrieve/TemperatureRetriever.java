package de.beosign.weatherstation.retrieve;


public interface TemperatureRetriever {

    Double retrieve() throws RetrieveException;

}
