package de.beosign.test.weatherstation.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import de.beosign.test.weatherstation.common.TemperatureUtil;
import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;

public class RestReading {
    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void setup() {
        Logger logger = LoggerFactory.getLogger(RestReading.class);

        context = SpringApplication.run(Application.class);
        logger.info("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }

        TemperatureReadingRepository repository = context.getBean(TemperatureReadingRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        repository.save(tr);
    }

    @AfterClass
    public static void tearDown() {
        context.close();
    }

    @Test
    public void callServlet() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
        messageConverters.add(map);
        template.setMessageConverters(messageConverters);
        // HttpEntity<String> requestEntity = new HttpEntity<String>(RestDataFixture.standardOrderJSON(), headers);

        ResponseEntity<TemperatureReading> entity = template.getForEntity("http://localhost:8080/temperaturereadings/1", TemperatureReading.class);

        System.out.println(entity);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        TemperatureReading tr = entity.getBody();

        System.out.println("The temperature reading is " + tr);

    }
}
