package Simulation;

import Simulation.REST.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import static Simulation.Util.Environment.getHealthCheckUrl;

public class Simulator {
    private static final Logger log = LogManager.getLogger(Simulator.class);

    private final String baseUrl;
    private final RequestHandler requestHandler;

    public Simulator(String baseUrl) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler();
    }

    public void startSimulation() {
        try {
            healthCheck();
        } catch (Exception ex) {
            log.info("Stopping simulation for: {}", baseUrl);
        }
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
}
