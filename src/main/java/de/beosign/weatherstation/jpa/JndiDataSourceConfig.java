package de.beosign.weatherstation.jpa;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

/**
 * JNDI files do not exist yet!
 * 
 * @author florian
 */
public class JndiDataSourceConfig implements DataSourceConfig {

    @Override
    @Bean
    public DataSource dataSource() throws DataSourceConfigException {
        Context ctx;
        try {

            ctx = new InitialContext();
            return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
        } catch (NamingException e) {
            throw new DataSourceConfigException(e);
        }
    }
}
