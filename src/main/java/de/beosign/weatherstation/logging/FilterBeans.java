package de.beosign.weatherstation.logging;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterBeans {

    @Bean
    protected FilterRegistrationBean createTemperaturFilterRegistrationBean() {
        return new FilterRegistrationBean(new TraceServletFilter());
    }

    @Bean
    protected FilterRegistrationBean createLoggingFilterRegistrationBean() {
        return new FilterRegistrationBean(new LoggingFilter());
    }

}
