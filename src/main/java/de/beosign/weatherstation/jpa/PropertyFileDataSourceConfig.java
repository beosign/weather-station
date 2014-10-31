package de.beosign.weatherstation.jpa;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import de.beosign.weatherstation.properties.DatabaseProperties;

/**
 * Creates datasource from {@link DatabaseProperties} instance.
 * 
 * @author Florian Dahlmanns
 */
@Configuration
public class PropertyFileDataSourceConfig implements DataSourceConfig {
    @Autowired
    private DatabaseProperties p;

    @Bean
    @Override
    @SuppressWarnings("unchecked")
    public DataSource dataSource() throws DataSourceConfigException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(p.getDriverClassName()));
            dataSource.setUrl(p.getUrl());
            dataSource.setUsername(p.getUsername());
            dataSource.setPassword(p.getPassword());
        } catch (ClassNotFoundException e) {
            throw new DataSourceConfigException(e);
        }

        return dataSource;
    }
}
