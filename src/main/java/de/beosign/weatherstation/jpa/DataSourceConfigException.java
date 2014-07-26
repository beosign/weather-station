package de.beosign.weatherstation.jpa;

public class DataSourceConfigException extends Exception {
    private static final long serialVersionUID = 1L;

    public DataSourceConfigException() {
        super();
    }

    public DataSourceConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataSourceConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceConfigException(String message) {
        super(message);
    }

    public DataSourceConfigException(Throwable cause) {
        super(cause);
    }

}
