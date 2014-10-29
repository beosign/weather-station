package de.beosign.weatherstation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class HttpProperties {
    private String baseurl;

    private Temperature temperature;

    @ConfigurationProperties(prefix = "temperature")
    public static class Temperature {
        private String context;
        private long queryInterval;
        private Basicauth basicauth;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        @ConfigurationProperties(prefix = "basicauth")
        public static class Basicauth {
            private String username;
            private String password;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            @Override
            public String toString() {
                return "Basicauth [username=" + username + ", password=" + password + "]";
            }
        }

        public Basicauth getBasicauth() {
            return basicauth;
        }

        public void setBasicauth(Basicauth basicauth) {
            this.basicauth = basicauth;
        }

        @Override
        public String toString() {
            return "Temperature [context=" + context + ", queryInterval=" + queryInterval + ", basicauth=" + basicauth + "]";
        }

        public long getQueryInterval() {
            return queryInterval;
        }

        public void setQueryInterval(long queryInterval) {
            this.queryInterval = queryInterval;
        }

    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "HttpProperties [baseurl=" + baseurl + ", temperature=" + temperature + "]";
    }

}
