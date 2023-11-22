package Simulation.Util;

public class Environment {
    private static final String RATER_MANAGEMENT_HOST = "188.166.9.118";
    private static final String RATER_MANAGEMENT_PORT = "8080";
    private static final String RATER_MANAGEMENT_URL = "rater/api/v1";

    private static final String RATER_RATE_CONTROL_HOST = "167.172.62.246";
    private static final String RATER_RATE_CONTROL_PORT = "8080";
    private static final String RATER_RATE_CONTROL_URL = "rater-rate-control/api/v1";

    public static final String getRateManagementUrl() {
        return "http://" + RATER_MANAGEMENT_HOST + ":" + RATER_MANAGEMENT_PORT + "/" + RATER_MANAGEMENT_URL;
    }

    public static final String getRateControlUrl() {
        return "http://" + RATER_RATE_CONTROL_HOST + ":" + RATER_RATE_CONTROL_PORT + "/" + RATER_RATE_CONTROL_URL;
    }

    public static final String getHealthCheckManagement() {
        return getRateManagementUrl() + "/health";
    }

    public static final String getHealthCheckRateControl() {
        return getRateControlUrl() + "/health";
    }

    public static final String getHealthCheckUrl(String baseUrl) {
        return baseUrl + "/health";
    }
}
