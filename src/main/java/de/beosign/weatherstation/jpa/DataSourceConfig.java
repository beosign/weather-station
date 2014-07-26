package de.beosign.weatherstation.jpa;

import javax.sql.DataSource;

public interface DataSourceConfig {
    DataSource dataSource() throws DataSourceConfigException;
}
