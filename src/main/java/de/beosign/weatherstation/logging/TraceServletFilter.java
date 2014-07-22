package de.beosign.weatherstation.logging;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.beosign.weatherstation.Application;

public final class TraceServletFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("init");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("do filter");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }
}
