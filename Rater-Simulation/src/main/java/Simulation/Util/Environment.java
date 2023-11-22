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

    public static final String getProcessRequestUrl(String baseUrl) {
        return baseUrl + "/process";
    }

    public static final String getRequestStatusUrl(String baseUrl) {
        return baseUrl + "/process/status";
    }

    public static final String getApiKey() {
        return "9ozW6brvKn8efxAdg40y3R6thfa89gYthMkBx1T91rc=";
    }

    public static final String getUserId() {
        return "4b7dbb41-4156-4a81-af4d-6052080104c7";
    }

    public static final String getApiPath() {
        return "POST:/apiTesting33";
    }

    public static final String getAuth() {
        return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZW1haWwyQGdtYWlsLmNvbSIsImlhdCI6MTcwMDY1Mjg5MiwiZXhwIjoxNzAwNzM5MjkyfQ.Ux-tW4jYvuFg-epiD_8-F8hrOz_jlw13JScb_B6DIrBGrnaXyMaHgVPu-9KK4VcxHGPRVwWJ1T4CxPW5FDL0AA";
    }
}
