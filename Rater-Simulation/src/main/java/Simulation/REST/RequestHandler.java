package Simulation.REST;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class RequestHandler {
    public JSONObject getResource(String url, String auth) throws IOException {
        JSONObject jsonObject = null;
        try {
            HttpResponse response;
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet getConnection = new HttpGet(url);
            if (auth != null && !auth.isEmpty()) {
                getConnection.setHeader("Authorization", "Bearer " + auth);
            }
            try {
                response = httpClient.execute(getConnection);
                String JSONString = EntityUtils.toString(response.getEntity(), "UTF-8");
                jsonObject = new JSONObject(JSONString);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return jsonObject;
    }

    public JSONObject postResource(String url, JSONObject requestBody) throws IOException {
        JSONObject jsonObject = null;
        try {
            HttpResponse response;
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost postConnection = new HttpPost(url);
            StringEntity entity = new StringEntity(requestBody.toString());
            entity.setContentType("application/json");
            postConnection.setEntity(entity);
            try {
                response = httpClient.execute(postConnection);
                String JSONString = EntityUtils.toString(response.getEntity(), "UTF-8");
                jsonObject = new JSONObject(JSONString);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
        return jsonObject;
    }
}
