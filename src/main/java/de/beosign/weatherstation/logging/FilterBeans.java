package de.beosign.weatherstation.logging;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterBeans {

    @Bean
    protected FilterRegistrationBean createTemperaturFilterRegistrationBean() {
        FilterRegistrationBean b = new FilterRegistrationBean(new TraceServletFilter());

        b.addInitParameter("initparam", "test1");
        b.addUrlPatterns("/temperaturereadings");
        b.addUrlPatterns("/temperaturereadings/*");

        return b;

    }

    @Bean
    protected FilterRegistrationBean createLoggingFilterRegistrationBean() {
        FilterRegistrationBean b = new FilterRegistrationBean(new LoggingFilter());

        b.addInitParameter("initparam", "test1");
        b.addUrlPatterns("/temperaturereadings");
        b.addUrlPatterns("/temperaturereadings/*");

        return b;

    }

}
