package de.beosign.weatherstation.logging;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.ServletContextRequestLoggingFilter;

import de.beosign.weatherstation.Application;

public final class TraceServletFilter extends ServletContextRequestLoggingFilter {
    private static final String MDC_REQID = "REQID";
    private static final AtomicLong INST_COUNTER = new AtomicLong();
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private static final ThreadLocal<Long> TIME = new ThreadLocal<Long>();

    public TraceServletFilter() {
        LOGGER.trace("Instance created: " + INST_COUNTER.incrementAndGet());
        setIncludeClientInfo(true);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        TIME.set(System.currentTimeMillis());
        MDC.put(MDC_REQID, System.currentTimeMillis() % 100000);
        LOGGER.debug(message);

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

        LOGGER.debug(message);

        Long interval = System.currentTimeMillis() - TIME.get();
        LOGGER.trace("Invovation time of servlet: " + interval + " ms");

        TIME.set(null);
        MDC.remove(MDC_REQID);

    }
}
