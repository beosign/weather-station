package de.beosign.weatherstation.jpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import de.beosign.weatherstation.properties.DatabaseProperties;
import de.beosign.weatherstation.spring.Production;

@Configuration
@Production
public class PropertyFileDataSourceConfig implements DataSourceConfig {
    @Autowired
    private DatabaseProperties p;

    @Override
    @Bean
    public DataSource dataSource() throws DataSourceConfigException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(p.getDriverClassName());
        dataSource.setUrl(p.getUrl());
        dataSource.setUsername(p.getUsername());
        dataSource.setPassword(p.getPassword());

        return dataSource;
    }
}
