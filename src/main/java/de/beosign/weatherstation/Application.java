package de.beosign.weatherstation;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableAspectJAutoProxy
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.debug("Started");
    }

    @Bean
    public CustomizableTraceInterceptor interceptor() {

        CustomizableTraceInterceptor interceptor = new CustomizableTraceInterceptor() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void writeToLog(Log logger, String message, Throwable ex) {
                if (ex != null) {
                    LOGGER.trace(message, ex);
                } else {
                    LOGGER.trace(message);
                }
            }
        };
        interceptor.setEnterMessage("Entering $[targetClassName]::$[methodName]($[arguments]).");
        interceptor.setExitMessage("Leaving $[targetClassName]::$[methodName](..) with return value $[returnValue], took $[invocationTime]ms.");

        return interceptor;
    }

    @Bean
    public Advisor traceAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * org.springframework.data.repository.Repository+.*(..))");

        return new DefaultPointcutAdvisor(pointcut, interceptor());
    }

    @Bean
    protected FilterRegistrationBean createFilterRegistration() {
        FilterRegistrationBean b = new FilterRegistrationBean(createTestFilter());

        b.addInitParameter("initparam", "test1");
        b.addUrlPatterns("/temperaturereadings");
        b.addUrlPatterns("/temperaturereadings/*");

        return b;

    }

    protected Filter createTestFilter() {
        LOGGER.debug("FILTER");
        return new Filter() {

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                LOGGER.debug("init");

            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                LOGGER.debug("do filter");
                chain.doFilter(request, response);

            }

            @Override
            public void destroy() {
            }
        };
    }
}
