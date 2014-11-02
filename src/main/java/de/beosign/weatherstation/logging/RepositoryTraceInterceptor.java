package de.beosign.weatherstation.logging;

import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * Custom implementation that uses a SLF4-Logger instead of apache commons logger when tracing calls to methods of a CrudRepository.
 * 
 * @author Florian Dahlmanns
 */
public class RepositoryTraceInterceptor extends CustomizableTraceInterceptor {
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
            de.beosign.weatherstation.logging.Log.logger().trace(message, ex);
        } else {
            de.beosign.weatherstation.logging.Log.logger().trace(message);
        }
    }

}
