package de.beosign.weatherstation.retrieve;

/**
 * Classes that retrieve a value from a sensor should extend/implement that interface.
 * 
 * @author Florian Dahlmanns
 * @param <T> The type of the value that is retrieved, e.g. {@link Double} for a temperature.
 */
public interface Retriever<T> {
    /**
     * Retrieves a value from a sensor.
     * 
     * @return the value
     */
    T retrieve();
}
