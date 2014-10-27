package de.beosign.weatherstation.jpa;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Common superclass for entities. Defines the id and version fields.
 * 
 * @author Florian Dahlmanns
 */
@MappedSuperclass
public class JPAEntity {
    private Long id;
    private Long version;

    /**
     * No-arg constructor is necessary for JPA.
     */
    protected JPAEntity() {

    }

    /**
     * Returns the id value.
     * 
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * Returns the version.
     * 
     * @return version
     */
    @Version
    public Long getVersion() {
        return version;
    }

    /**
     * Necessary for JPA.
     * 
     * @param id id
     */
    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * Necessary for JPA.
     * 
     * @param version version
     */
    @SuppressWarnings("unused")
    private void setVersion(Long version) {
        this.version = version;
    }
}
