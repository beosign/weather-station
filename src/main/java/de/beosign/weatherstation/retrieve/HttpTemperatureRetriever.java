package de.beosign.weatherstation.retrieve;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.beosign.weatherstation.properties.AppProperties;

@Component
public class HttpTemperatureRetriever implements TemperatureRetriever {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpTemperatureRetriever.class);

    @Autowired
    private AppProperties p;

    /* (non-Javadoc)
     * @see de.beosign.weatherstation.reading.TemperatureRetriever#retrieveTemperature()
     */
    @Override
    public Double retrieve() throws RetrieveException {

        Double temp;
        try (CloseableHttpClient httpClient = createHttpClient()) {

            temp = Double.NaN;
            HttpGet get = new HttpGet(p.getHttpBaseurl() + p.getHttpTemperatureContext());
            try (CloseableHttpResponse r = httpClient.execute(get)) {
                String strTemp = IOUtils.toString(r.getEntity().getContent());
                temp = Double.valueOf(strTemp);
            }
            LOGGER.debug("Temperature read: " + temp);
        } catch (KeyManagementException | NumberFormatException | NoSuchAlgorithmException | IllegalStateException | IOException e) {
            throw new RetrieveException(e);
        }

        return temp;

    }

    private CloseableHttpClient createHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        HttpClientBuilder builder = HttpClientBuilder.create();

        SSLContext sslContext;

        sslContext = SSLContext.getInstance("SSL");
        // set up a TrustManager that trusts everything
        sslContext.init(null, new TrustManager[] { new X509TrustManager() {

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

        } }, new SecureRandom());

        builder.setSslcontext(sslContext);

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        Credentials credentials = new UsernamePasswordCredentials(p.getHttpTemperatureBasicauthUsername(), p.getHttpTemperatureBasicauthPassword());
        AuthScope authScope = AuthScope.ANY;
        credentialsProvider.setCredentials(authScope, credentials);
        builder.setDefaultCredentialsProvider(credentialsProvider);

        CloseableHttpClient httpClient = builder.build();

        return httpClient;
    }
}
