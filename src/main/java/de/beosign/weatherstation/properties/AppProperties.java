package de.beosign.weatherstation.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({ @PropertySource("classpath:/app.properties") })
public class AppProperties {

    @Autowired
    private Environment env;

    @Value("${http.baseurl}")
    private String httpBaseurl;

    @Value("${http.temperature.context}")
    private String httpTemperatureContext;

    @Value("${http.temperature.basicauth.username}")
    private String httpTemperatureBasicauthUsername;

    @Value("${http.temperature.basicauth.password}")
    private String httpTemperatureBasicauthPassword;

    public String getHttpBaseurl() {
        return httpBaseurl;
    }

    public String getHttpTemperatureContext() {
        return httpTemperatureContext;
    }

    public String getHttpTemperatureBasicauthUsername() {
        return httpTemperatureBasicauthUsername;
    }

    public String getHttpTemperatureBasicauthPassword() {
        return httpTemperatureBasicauthPassword;
    }

}
