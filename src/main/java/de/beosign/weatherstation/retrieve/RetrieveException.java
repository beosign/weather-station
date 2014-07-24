package de.beosign.weatherstation.retrieve;

public class RetrieveException extends Exception {
    private static final long serialVersionUID = 1L;

    public RetrieveException() {
        super();
    }

    public RetrieveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RetrieveException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetrieveException(String message) {
        super(message);
    }

    public RetrieveException(Throwable cause) {
        super(cause);
    }

}
