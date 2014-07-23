package de.beosign.weatherstation.logging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.web.filter.GenericFilterBean;

public class LoggingFilter extends GenericFilterBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ByteArrayOutputStream responseBaos = new ByteArrayOutputStream();
        ByteArrayOutputStream requestBaos = new ByteArrayOutputStream();

        HttpServletResponse responseWrapper = loggingResponseWrapper((HttpServletResponse) response, responseBaos);

        HttpServletRequest requestWrapper = loggingRequestWrapper((HttpServletRequest) request, requestBaos);

        LOGGER.trace("Request:\n" + requestBaos.toString());
        filterChain.doFilter(requestWrapper, responseWrapper);
        LOGGER.trace("Response:\n" + responseBaos.toString());

    }

    private HttpServletResponse loggingResponseWrapper(HttpServletResponse response, ByteArrayOutputStream responseBaos) {

        return new HttpServletResponseWrapper(response) {
            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return new DelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), responseBaos));
            }
        };
    }

    private HttpServletRequest loggingRequestWrapper(HttpServletRequest request, ByteArrayOutputStream requestBaos) {

        return new HttpServletRequestWrapper(request) {
            @Override
            public ServletInputStream getInputStream() throws IOException {
                return new DelegatingServletInputStream(new TeeInputStream(super.getInputStream(), requestBaos));
            }

        };
    }

}
