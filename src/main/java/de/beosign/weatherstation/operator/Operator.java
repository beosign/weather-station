package de.beosign.weatherstation.operator;

import de.beosign.weatherstation.retrieve.Retriever;

/**
 * An operator has basically two concerns: Retrieve the result and store it.
 * 
 * @author Florian Dahlmanns
 * @param <T> The type of the retrieved data
 */
public interface Operator<T> {
    /**
     * Returns the concrete {@link Retriever} instance used to retrieve the data.
     * 
     * @return the concrete {@link Retriever} instance
     */
    Retriever<T> getRetriever();

    /**
     * Retrieve and store the result.
     */
    void retrieveAndStore();

}
