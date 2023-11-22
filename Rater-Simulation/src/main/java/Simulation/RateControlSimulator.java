package Simulation;

import Simulation.Models.ApiStatus;
import Simulation.REST.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static Simulation.Util.Environment.*;

public class RateControlSimulator {
    private static final Logger log = LogManager.getLogger(RateControlSimulator.class);

    private final String baseUrl;
    private final RequestHandler requestHandler;

    public RateControlSimulator(String baseUrl) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler();
    }

    public void startSimulation() throws InterruptedException {
        try {
            healthCheck();
        } catch (Exception ex) {
            log.info("Stopping simulation for: {}", baseUrl);
        }

        int requestCount = 100;
        log.info("Sending {} Requests", requestCount);
        Instant startTime = Instant.now();

        sendRequests(requestCount);

        Instant endTime = Instant.now();
        log.info("Sent {} Requests in: {}ms", requestCount, Duration.between(startTime, endTime).toMillis());

        Thread.sleep(500);
        log.info("Status Retrieved: {}", getStatus().toString());
    }

    private void healthCheck() throws Exception {
        String url = getHealthCheckUrl(baseUrl);
        try {
            JSONObject json = requestHandler.getResource(url, null);
            if (!json.get("healthStatus").toString().equals("healthy")) {
                throw new Exception("Health Check Failed");
            } else {
                log.info("Health Check Successful for: {}", baseUrl);
            }
        } catch (Exception ex) {
            log.info("Health Check Failed for: {}", baseUrl);
            throw ex;
        }
    }

    private void sendRequests(int n) {
        //Sends requests to impersonate a server receiving a request from a client
        List<CompletableFuture<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            CompletableFuture<Void> task = CompletableFuture.runAsync(() -> sendRequest());
            tasks.add(task);
        }

        CompletableFuture<Void>[] tasksArray = tasks.toArray(new CompletableFuture[0]);
        CompletableFuture.allOf(tasksArray).join();
    }

    private void sendRequest() {
        String url = getProcessRequestUrl(baseUrl);
        JSONObject requestBody = getRequestBodyForProcessingAndStatus();

        try {
            Instant startTime = Instant.now();
            JSONObject json = requestHandler.postResource(url, getAuth(), requestBody);
            if (json != null && (json.optString("status").equals("")) || json.toMap().get("status").equals(200))  {
                Instant endTime = Instant.now();
                String successString = json.getBoolean("rateExceeded") ? "denied" : "permitted";
                log.info("Request {} Successfully in {}ms to: {}", successString, Duration.between(startTime, endTime).toMillis(), url);
            } else {
                throw new Exception("Request Failed");
            }
        } catch (Exception ex) {
            log.info("Request Failed: {}", baseUrl);
        }
    }

    private ApiStatus getStatus() {
        String url = getRequestStatusUrl(baseUrl);
        JSONObject requestBody = getRequestBodyForProcessingAndStatus();

        try {
            JSONObject json = requestHandler.postResource(url, getAuth(), requestBody);
            if (json != null && (json.optString("status").equals("")) || json.toMap().get("status").equals(200))  {
                return ApiStatus.from(json);
            } else {
                throw new Exception("Status Request Failed");
            }
        } catch (Exception ex) {
            log.info("Status Request Failed: {}", baseUrl);
        }
        return null;
    }

    private JSONObject getRequestBodyForProcessingAndStatus() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("apiKey", getApiKey());
        requestBody.put("apiPath", getApiPath());
        requestBody.put("userId", getUserId());
        return requestBody;
    }
}
