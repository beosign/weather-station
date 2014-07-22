package de.beosign.weatherstation;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.debug("Started");
    }

    @Bean
    protected FilterRegistrationBean createFilterRegistration() {
        FilterRegistrationBean b = new FilterRegistrationBean(createTestFilter());

        b.addInitParameter("initparam", "test1");
        b.addUrlPatterns("/temperaturereadings");
        b.addUrlPatterns("/temperaturereadings/*");

        return b;

    }

    protected Filter createTestFilter() {
        LOGGER.debug("FILTER");
        return new Filter() {

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
        };
    }
}
