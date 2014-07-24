package de.beosign.weatherstation.logging;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

import de.beosign.weatherstation.Application;

public class RepositoryTraceInterceptor extends CustomizableTraceInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if (ex != null) {
            LOGGER.trace(message, ex);
        } else {
            LOGGER.trace(message);
        }
    }

}
