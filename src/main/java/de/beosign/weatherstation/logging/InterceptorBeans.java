package de.beosign.weatherstation.logging;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorBeans {
    @Bean
    public RepositoryTraceInterceptor repositoryTraceInterceptor() {

        RepositoryTraceInterceptor interceptor = new RepositoryTraceInterceptor();
        interceptor.setEnterMessage("Entering $[targetClassName]::$[methodName]($[arguments]).");
        interceptor.setExitMessage("Leaving $[targetClassName]::$[methodName](..) with return value $[returnValue], took $[invocationTime]ms.");

        return interceptor;
    }

    @Bean
    public Advisor repositoryTraceAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * org.springframework.data.repository.Repository+.*(..))");

        return new DefaultPointcutAdvisor(pointcut, repositoryTraceInterceptor());
    }
}
