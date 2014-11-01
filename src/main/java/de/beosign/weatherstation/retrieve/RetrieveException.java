package de.beosign.weatherstation.retrieve;

/**
 * Thrown to indicate that a retrieval operation from a sensor could not be executed. This exception is usually thrown from a {@link Retriever}.
 * 
 * @author Florian Dahlmanns
 */
public class RetrieveException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new retrieve exception.
     */
    public RetrieveException() {
        super();
    }

    /**
     * Instantiates a new retrieve exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public RetrieveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new retrieve exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public RetrieveException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new retrieve exception.
     *
     * @param message the message
     */
    public RetrieveException(String message) {
        super(message);
    }

    /**
     * Instantiates a new retrieve exception.
     *
     * @param cause the cause
     */
    public RetrieveException(Throwable cause) {
        super(cause);
    }

}
