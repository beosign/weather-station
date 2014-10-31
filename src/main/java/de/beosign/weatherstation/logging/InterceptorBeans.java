package de.beosign.weatherstation.logging;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Defines beans regarding interception.
 * 
 * @author Florian Dahlmanns
 */
@Configuration
public class InterceptorBeans {
    /**
     * Creates a repository trace interceptor.
     * 
     * @return {@link RepositoryTraceInterceptor} instance
     */
    @Bean
    public RepositoryTraceInterceptor repositoryTraceInterceptor() {

        RepositoryTraceInterceptor interceptor = new RepositoryTraceInterceptor();
        interceptor.setEnterMessage("Entering $[targetClassName]::$[methodName]($[arguments]).");
        interceptor.setExitMessage("Leaving $[targetClassName]::$[methodName](..) with return value $[returnValue], took $[invocationTime]ms.");

        return interceptor;
    }

    /**
     * Creates a repository trace advisor.
     *
     * @return repository trace advisor.
     */
    @Bean
    public Advisor repositoryTraceAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * org.springframework.data.repository.Repository+.*(..))");

        return new DefaultPointcutAdvisor(pointcut, repositoryTraceInterceptor());
    }

    // /**
    // * Creates a repository trace advisor.
    // *
    // * @return repository trace advisor.
    // */
    // @Bean
    // public Advisor mathodTraceAdvisor() {
    //
    // AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    // pointcut.setExpression("execution(* de.beosign..*.*(..))");
    //
    // return new DefaultPointcutAdvisor(pointcut, repositoryTraceInterceptor());
    // }
}
