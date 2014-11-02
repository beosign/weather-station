package de.beosign.test.weatherstation.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test date and time functions.
 * 
 * @author Florian Dahlmanns
 */
public class DateTimeTest {

    /**
     * Convert UTC to local time.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void convertUTCToLocal() {
        int offsetSeconds = ZoneId.systemDefault().getRules().getOffset(Instant.now()).getTotalSeconds();

        ZonedDateTime date = ZonedDateTime.parse("2014-11-01T11:54:23+00:00");
        System.out.println(date);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime);

        Date res = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(res);

        Assert.assertEquals(res.getHours(), date.getHour() + offsetSeconds / 3600.0, 1);
        Assert.assertEquals(localDateTime.getHour(), date.getHour() + offsetSeconds / 3600.0, 1);

    }

}
