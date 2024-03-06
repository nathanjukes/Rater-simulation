package Simulation.REST;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.IOException;

public class RequestHandler implements Closeable {
    private final CloseableHttpClient httpClient;

    public RequestHandler() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();

        HttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        this.httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

    public JSONObject getResource(String url, String auth) throws IOException {
        JSONObject jsonObject = null;
        try {
            HttpResponse response;
            HttpGet getConnection = new HttpGet(url);
            if (auth != null && !auth.isEmpty()) {
                getConnection.setHeader("Authorization", "Bearer " + auth);
            }
            try {
                response = httpClient.execute(getConnection);
                String JSONString = EntityUtils.toString(response.getEntity(), "UTF-8");
                jsonObject = new JSONObject(JSONString);
            } catch (IOException e) {
                throw e;
            }
        } catch (IOException e) {
            throw e;
        }
        return jsonObject;
    }

    public JSONObject postResource(String url, JSONObject requestBody) throws IOException {
        JSONObject jsonObject = null;
        try {
            HttpResponse response;
            HttpPost postConnection = new HttpPost(url);
            StringEntity entity = new StringEntity(requestBody.toString());
            entity.setContentType("application/json");
            postConnection.setEntity(entity);
            try {
                response = httpClient.execute(postConnection);
                String JSONString = EntityUtils.toString(response.getEntity(), "UTF-8");
                jsonObject = new JSONObject(JSONString);
            } catch (IOException e) {
                throw e;
            }
        } catch (IOException e) {
            throw e;
        }
        return jsonObject;
    }

    @Override
    public void close() throws IOException {
        try {
            httpClient.close();
        } catch (IOException e) {
            // Handle the exception or log it if necessary
            throw e;
        }
    }
}
