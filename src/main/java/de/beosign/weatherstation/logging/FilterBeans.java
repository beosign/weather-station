package de.beosign.weatherstation.logging;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines servlet filters.
 * 
 * @author Florian Dahlmanns
 */
@Configuration
public class FilterBeans {

    /**
     * Create a {@link TraceServletFilter}.
     * 
     * @return {@link TraceServletFilter}
     */
    @Bean
    protected FilterRegistrationBean traceServletFilterRegistrationBean() {
        return new FilterRegistrationBean(new TraceServletFilter());
    }

}
