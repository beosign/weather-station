package de.beosign.weatherstation.retrieve;

public interface Retriever<T> {
    /**
     * Retrieves a value from a sensor.
     * 
     * @return the value
     * @throws {@link RetrieveException} if an error occurred.
     */
    T retrieve();
}
