package de.beosign.weatherstation.logging;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

import de.beosign.weatherstation.Application;

/**
 * Custom implementation that uses a SLF4-Logger instead of apache commons logger when tracing calls to methods of a CrudRepository.
 * 
 * @author Florian Dahlmanns
 */
public class RepositoryTraceInterceptor extends CustomizableTraceInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final long serialVersionUID = 1L;

    /**
     * Sets options: hide proxy class names.
     */
    public RepositoryTraceInterceptor() {
        setHideProxyClassNames(true);
    }

    @Override
    protected void writeToLog(Log logger, String message, Throwable ex) {
        if (ex != null) {
            LOGGER.trace(message, ex);
        } else {
            LOGGER.trace(message);
        }
    }

}
