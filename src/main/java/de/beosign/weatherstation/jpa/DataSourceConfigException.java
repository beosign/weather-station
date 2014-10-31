package de.beosign.weatherstation.jpa;

/**
 * The Class DataSourceConfigException.
 */
public class DataSourceConfigException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new data source config exception.
     */
    public DataSourceConfigException() {
        super();
    }

    /**
     * Instantiates a new data source config exception.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public DataSourceConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new data source config exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public DataSourceConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new data source config exception.
     *
     * @param message the message
     */
    public DataSourceConfigException(String message) {
        super(message);
    }

    /**
     * Instantiates a new data source config exception.
     *
     * @param cause the cause
     */
    public DataSourceConfigException(Throwable cause) {
        super(cause);
    }

}
