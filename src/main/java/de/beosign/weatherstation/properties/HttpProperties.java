package de.beosign.weatherstation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Base class for reading HttpProperties from file.
 */
@Configuration
public abstract class HttpProperties {
    private String baseurl;

    private Temperature temperature;

    /**
     * The Class Temperature.
     */
    @ConfigurationProperties(prefix = "temperature")
    public static class Temperature {
        private String context;
        private long queryInterval;
        private Basicauth basicauth;

        /**
         * Gets the context.
         *
         * @return the context
         */
        public String getContext() {
            return context;
        }

        /**
         * Sets the context.
         *
         * @param context the new context
         */
        public void setContext(String context) {
            this.context = context;
        }

        /**
         * The Class Basicauth.
         */
        @ConfigurationProperties(prefix = "basicauth")
        public static class Basicauth {
            private String username;
            private String password;

            /**
             * Gets the username.
             *
             * @return the username
             */
            public String getUsername() {
                return username;
            }

            /**
             * Sets the username.
             *
             * @param username the new username
             */
            public void setUsername(String username) {
                this.username = username;
            }

            /**
             * Gets the password.
             *
             * @return the password
             */
            public String getPassword() {
                return password;
            }

            /**
             * Sets the password.
             *
             * @param password the new password
             */
            public void setPassword(String password) {
                this.password = password;
            }

            /* (non-Javadoc)
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Basicauth [username=" + username + ", password=" + password + "]";
            }
        }

        /**
         * Gets the basicauth.
         *
         * @return the basicauth
         */
        public Basicauth getBasicauth() {
            return basicauth;
        }

        /**
         * Sets the basicauth.
         *
         * @param basicauth the new basicauth
         */
        public void setBasicauth(Basicauth basicauth) {
            this.basicauth = basicauth;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Temperature [context=" + context + ", queryInterval=" + queryInterval + ", basicauth=" + basicauth + "]";
        }

        /**
         * Gets the query interval.
         *
         * @return the query interval
         */
        public long getQueryInterval() {
            return queryInterval;
        }

        /**
         * Sets the query interval.
         *
         * @param queryInterval the new query interval
         */
        public void setQueryInterval(long queryInterval) {
            this.queryInterval = queryInterval;
        }

    }

    /**
     * Gets the baseurl.
     *
     * @return the baseurl
     */
    public String getBaseurl() {
        return baseurl;
    }

    /**
     * Sets the baseurl.
     *
     * @param baseurl the new baseurl
     */
    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    /**
     * Gets the temperature.
     *
     * @return the temperature
     */
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature.
     *
     * @param temperature the new temperature
     */
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpProperties [baseurl=" + baseurl + ", temperature=" + temperature + "]";
    }

}
