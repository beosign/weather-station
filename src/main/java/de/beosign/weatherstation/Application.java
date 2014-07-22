package de.beosign.weatherstation;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import de.beosign.weatherstation.logging.TraceServletFilter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableAspectJAutoProxy
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
        return new TraceServletFilter();
    }
}
