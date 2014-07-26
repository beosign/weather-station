package de.beosign.test.weatherstation.jpa;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import de.beosign.test.weatherstation.common.TemperatureUtil;
import de.beosign.weatherstation.Application;
import de.beosign.weatherstation.reading.TemperatureReading;
import de.beosign.weatherstation.reading.TemperatureReadingRepository;

public class JPATemperatureReading {
    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void setup() {
        context = SpringApplication.run(Application.class);

    }

    @AfterClass
    public static void tearDown() {
        context.close();

    }

    @Test
    public void saveTemperatureReading() {
        TemperatureReadingRepository repository = context.getBean(TemperatureReadingRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        repository.save(tr);

    }

    @Test
    public void getReading() {
        TemperatureReadingRepository repository = context.getBean(TemperatureReadingRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        repository.save(tr);

        TemperatureReading tempRead = repository.findOne(tr.getId());

        Assert.assertTrue("Read one failed", tempRead != null);

    }

    @Test
    public void getReadingBetween() {
        TemperatureReadingRepository repository = context.getBean(TemperatureReadingRepository.class);

        TemperatureReading tr = TemperatureUtil.createTemperatureReading();
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 1));
        repository.save(tr);
        tr = TemperatureUtil.createTemperatureReading(new Date(System.currentTimeMillis() - 1000 * 3600 * 24 * 2));
        repository.save(tr);

        List<TemperatureReading> tempReads = repository.findByReadDateBetween(new Date((long) (System.currentTimeMillis() - 1000 * 3600 * 24 * 1.5)), new Date(
                System.currentTimeMillis() + 1000000));

        tempReads.forEach(System.out::println);
        Assert.assertTrue("Read all failed", tempReads != null);
        Assert.assertTrue("Size must be > 0", tempReads.size() > 0);

    }

}
