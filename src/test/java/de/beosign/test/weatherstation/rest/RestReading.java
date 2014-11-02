package de.beosign.test.weatherstation.rest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.test.weatherstation.common.TemperatureUtil;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;
import de.beosign.weatherstation.sensor.SensorRepository;

/**
 * Test REST functionality.
 * 
 * @author Florian Dahlmanns
 */
public class RestReading extends JUnitUtil {

    /**
     * Retrieves values from database via REST.
     */
    @Test
    public void callServlet() {
        TemperatureReadingRepository repository = getContext().getBean(TemperatureReadingRepository.class);
        SensorRepository sensorRepository = getContext().getBean(SensorRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        sensorRepository.save(tr.getSensor());
        repository.save(tr);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
        messageConverters.add(map);
        template.setMessageConverters(messageConverters);
        // HttpEntity<String> requestEntity = new HttpEntity<String>(RestDataFixture.standardOrderJSON(), headers);

        ResponseEntity<TemperatureReading> entity = template.getForEntity("http://localhost:8080/temperaturereadings/" + tr.getId(), TemperatureReading.class);

        LOGGER.info(entity.toString());

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        tr = entity.getBody();

        LOGGER.info("The temperature reading is " + tr);

    }
}
