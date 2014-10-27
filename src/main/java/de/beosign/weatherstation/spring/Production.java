package de.beosign.weatherstation.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Profile;

/**
 * Annotation that marks a bean relevant for the production profile.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile(SpringProfiles.PROFILE_PROD)
public @interface Production {
}
