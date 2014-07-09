package de.beosign.weatherstation;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static final class MyInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            System.out.println("Request from: " + request.getRemoteAddr());
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            System.out.println("Post Handle from: " + request.getRemoteAddr());

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            System.out.println("After completion from: " + request.getRemoteAddr());

        }

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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
        System.out.println("FILTER");
        return new Filter() {

            @Override
            public void init(FilterConfig filterConfig) throws ServletException {
                System.out.println("init");

            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("do filter");
                chain.doFilter(request, response);

            }

            @Override
            public void destroy() {
                // TODO Auto-generated method stub

            }
        };
    }
}
