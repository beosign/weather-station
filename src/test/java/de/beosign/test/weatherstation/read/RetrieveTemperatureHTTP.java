package de.beosign.test.weatherstation.read;

import org.junit.Assert;

import de.beosign.test.weatherstation.common.JUnitUtil;
import de.beosign.weatherstation.retrieve.HttpTemperatureRetriever;
import de.beosign.weatherstation.retrieve.TemperatureRetriever;

public class RetrieveTemperatureHTTP extends JUnitUtil {
    private TemperatureRetriever httpTemperatureReader;

    public void readTempHttp() {
        httpTemperatureReader = context.getBean(HttpTemperatureRetriever.class);
        Assert.assertNotNull(httpTemperatureReader);

        System.out.println(httpTemperatureReader.retrieve());

    }
}
