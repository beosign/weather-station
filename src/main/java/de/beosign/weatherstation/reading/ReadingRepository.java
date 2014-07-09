package de.beosign.weatherstation.reading;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReadingRepository<S extends Reading<?>> extends CrudRepository<S, Long> {
    // List<S> getBefore(Date date);
    //
    // List<Reading<S>> getAfter(Date date);
    //
    // List<Reading<S>> getBetween(Date fromDate, Date toDate);

}
