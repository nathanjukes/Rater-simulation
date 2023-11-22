package Simulation.Models;

import org.json.JSONObject;

import java.time.LocalDateTime;

public class ApiStatus {
    private String apiName;
    private boolean rateExceeded;
    private int apiLimit;
    private int currentLoad;

    public ApiStatus(String apiName, boolean rateExceeded, int currentLoad, int apiLimit) {
        this.apiName = apiName;
        this.rateExceeded = rateExceeded;
        this.apiLimit = apiLimit;
        this.currentLoad = currentLoad;
    }

    public String getApiName() {
        return apiName;
    }

    public boolean isRateExceeded() {
        return rateExceeded;
    }

    public int getLimit() {
        return apiLimit;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public static ApiStatus from(JSONObject jsonObject) {
        String apiName = jsonObject.getString("apiName");
        boolean rateExceeded = jsonObject.getBoolean("rateExceeded");
        int currentLoad = jsonObject.getInt("currentLoad");
        int apiLimit = jsonObject.getInt("limit");

        return new ApiStatus(apiName, rateExceeded, currentLoad, apiLimit);
    }

    @Override
    public String toString() {
        return "ApiStatus{" +
                "apiName='" + apiName + '\'' +
                ", rateExceeded=" + rateExceeded +
                ", apiLimit=" + apiLimit +
                ", currentLoad=" + currentLoad +
                '}';
    }
}

