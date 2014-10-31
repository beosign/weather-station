package de.beosign.weatherstation.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import com.ecyrd.speed4j.StopWatch;

import de.beosign.weatherstation.Application;

/**
 * Servlet filter for logging request and response payloads as TRACE log. Also measures time spent for the request to complete.
 * 
 * @author Florian Dahlmanns
 */
public final class TraceServletFilter extends AbstractRequestLoggingFilter {
    private static final int COUNTER_MODULUS = 10000;
    private static final String MDC_REQID = "REQID";
    private static final AtomicLong REQ_COUNTER = new AtomicLong();
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceServletFilter.class);

    /**
     * Sets logging options: include client info and query string while logging.
     */
    public TraceServletFilter() {
        setIncludeClientInfo(true);
        setIncludeQueryString(true);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        LOGGER.debug(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        LOGGER.debug(message);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String perfLogMessage = " From " + request.getRemoteAddr() + ", uri = " + request.getRequestURI();
        StopWatch sw = Application.getStopWatchFactory().getStopWatch();

        MDC.put(MDC_REQID, REQ_COUNTER.incrementAndGet() % COUNTER_MODULUS);

        ByteArrayOutputStream responseBaos = new ByteArrayOutputStream();
        ByteArrayOutputStream requestBaos = new ByteArrayOutputStream();

        HttpServletResponse responseWrapper = loggingResponseWrapper(response, responseBaos);
        HttpServletRequest requestWrapper = loggingRequestWrapper(request, requestBaos);

        Exception ex = null;
        LOGGER.trace("Request:\n" + requestBaos.toString());

        try {
            super.doFilterInternal(requestWrapper, responseWrapper, filterChain);
        } catch (Exception e) {
            ex = e;
            throw e;
        } finally {
            LOGGER.trace("Response:\n" + responseBaos.toString());

            perfLogMessage = perfLogMessage + ", request body size = " + requestBaos.size() + ", response body size = " + responseBaos.size();
            if (ex != null) {
                perfLogMessage = perfLogMessage + ", EXCEPTION: " + ex.getMessage();
            }

            sw.stop("HTTP-Request", perfLogMessage);
            MDC.remove(MDC_REQID);
        }

    }

    /**
     * Logging response wrapper.
     *
     * @param response the response
     * @param responseBaos the response baos
     * @return the http servlet response
     */
    private HttpServletResponse loggingResponseWrapper(HttpServletResponse response, ByteArrayOutputStream responseBaos) {

        return new HttpServletResponseWrapper(response) {

            /* (non-Javadoc)
             * @see javax.servlet.ServletResponseWrapper#getOutputStream()
             */
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), responseBaos));
            }
        };
    }

    /**
     * Logging request wrapper.
     *
     * @param request the request
     * @param requestBaos the request baos
     * @return the http servlet request
     */
    private HttpServletRequest loggingRequestWrapper(HttpServletRequest request, ByteArrayOutputStream requestBaos) {

        return new HttpServletRequestWrapper(request) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new DelegatingServletInputStream(new TeeInputStream(super.getInputStream(), requestBaos));
            }

        };
    }

}
