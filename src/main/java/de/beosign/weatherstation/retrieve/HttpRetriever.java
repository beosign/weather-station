package de.beosign.weatherstation.retrieve;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.HttpProperties;

/**
 * A retriever that uses HTTP to retrieve values.
 * 
 * @author Florian Dahlmanns
 * @param <T> The type of the retrieved data
 */
@Component
public abstract class HttpRetriever<T> extends AbstractRetriever<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRetriever.class);

    private HttpProperties httpProperties;

    /* (non-Javadoc)
     * @see de.beosign.weatherstation.reading.TemperatureRetriever#retrieveTemperature()
     */
    @Override
    public T retrieve() {

        T tr = null;
        try (CloseableHttpClient httpClient = createHttpClient()) {
            HttpGet get = new HttpGet(getHttpProperties().getBaseurl() + getHttpProperties().getTemperature().getContext());
            try (CloseableHttpResponse r = httpClient.execute(get)) {
                tr = extract(r.getEntity().getContent());
            }
            LOGGER.debug("Read: " + tr);
        } catch (KeyManagementException | NumberFormatException | NoSuchAlgorithmException | IllegalStateException | IOException e) {
            throw new RetrieveException(e);
        }

        return tr;

    }

    /**
     * Set HTTP properties.
     * 
     * @throws Exception on {@link Exception}.
     */
    @Override
    public void doAfterPropertiesSet() throws Exception {
        httpProperties = getHttpProperties();

    }

    /**
     * Subclasses must return their properties.
     * 
     * @return properties
     */
    protected abstract HttpProperties getHttpProperties();

    /**
     * Extract the data from the returned http response stream.
     * 
     * @param content content
     * @return extracted data
     * @throws IOException if an {@link IOException} occurred
     */
    protected abstract T extract(InputStream content) throws IOException;

    /**
     * Creates an http client. Sets features like SSL and Basic Authentication.
     *
     * @return http client
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws KeyManagementException the key management exception
     */
    protected CloseableHttpClient createHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        HttpClientBuilder builder = HttpClientBuilder.create();

        if (httpProperties.getBaseurl().toLowerCase().startsWith("https")) {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            // set up a TrustManager that trusts everything
            sslContext.init(createKeyManagers(), createTrustManagers(), new SecureRandom());

            builder.setSslcontext(sslContext);
        }

        if (httpProperties.getTemperature().getBasicauth() != null) {
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            Credentials credentials = new UsernamePasswordCredentials(httpProperties.getTemperature().getBasicauth().getUsername(), httpProperties
                    .getTemperature().getBasicauth().getPassword());
            AuthScope authScope = AuthScope.ANY;
            credentialsProvider.setCredentials(authScope, credentials);
            builder.setDefaultCredentialsProvider(credentialsProvider);
        }

        CloseableHttpClient httpClient = builder.build();

        return httpClient;
    }

    /**
     * By default, a {@link TrustManager} that accepts all certificates is returned.
     * 
     * @return TrustManager that trusts all certificates.
     */
    protected TrustManager[] createTrustManagers() {
        return new TrustManager[] { new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] ax509certificate, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

        } };
    }

    /**
     * Returns null. Can be overridden by subclasses.
     * 
     * @return null by default
     */
    protected KeyManager[] createKeyManagers() {
        return null;

    }

}
