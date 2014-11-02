package de.beosign.weatherstation.reading;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * POJO for storing values returned from OpenWeather.
 * 
 * @author Florian Dahlmanns
 */
public class OpenWeatherMapData {
    private Double temperature;
    private ZonedDateTime timestamp;

    /**
     * Creates an instance by parsing the given JSON string using a default deserializer.
     * 
     * @param json json
     * @return instance parsed from json
     * @throws IOException if an error occurs
     */
    public static OpenWeatherMapData fromJson(String json) throws IOException {
        return fromJson(json, createDefaultDeserializer());
    }

    /**
     * Creates an instance by parsing the given JSON string using the given deserializer.
     * 
     * @param json json
     * @param deserializer a custom {@link JsonDeserializer} instance.
     * @return instance parsed from json
     * @throws IOException if an error occurs
     */
    public static OpenWeatherMapData fromJson(String json, JsonDeserializer<OpenWeatherMapData> deserializer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OpenWeatherMapData.class, deserializer);
        mapper.registerModule(module);

        return mapper.readValue(json, OpenWeatherMapData.class);
    }

    /**
     * Gets the temperature.
     *
     * @return the temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature.
     *
     * @param temperature the new temperature
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp the new timestamp
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OpenWeatherMapData [temperature=" + temperature + ", timestamp=" + timestamp + "]";
    }

    /**
     * Creates a default Deserializer.
     * 
     * @return a default Deserializer.
     */
    private static JsonDeserializer<OpenWeatherMapData> createDefaultDeserializer() {
        return new JsonDeserializer<OpenWeatherMapData>() {

            @Override
            public OpenWeatherMapData deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

                JsonNode node = jp.getCodec().readTree(jp);
                double temp = node.get("main").get("temp").asDouble();
                long ts = node.get("dt").asLong();

                OpenWeatherMapData data = new OpenWeatherMapData();

                ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(ts), ZoneId.of("UTC"));
                data.setTimestamp(zonedDateTime);
                data.setTemperature(temp);
                return data;

            }

        };
    }

}
