package de.beosign.weatherstation.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides access to loggers. Use this instead of declaring a logger instance in each class.
 * 
 * @author Florian Dahlmanns
 */
public final class Log {

    /**
     * Private constructor.
     */
    private Log() {
    }

    /**
     * Returns a logger where the logger name is determined by looking at the stacktrace.
     * 
     * @return logger
     */
    public static Logger logger() {
        String callingClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        return LoggerFactory.getLogger(callingClassName);
    }

}
