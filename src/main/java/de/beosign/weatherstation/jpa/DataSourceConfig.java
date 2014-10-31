package de.beosign.weatherstation.jpa;

import javax.sql.DataSource;

/**
 * Datasource configuration.
 * 
 * @author Florian Dahlmanns
 */
public interface DataSourceConfig {
    /**
     * Create data soruce.
     * 
     * @return DataSource instance
     * @throws DataSourceConfigException if something went wrong.
     */
    DataSource dataSource() throws DataSourceConfigException;
}
