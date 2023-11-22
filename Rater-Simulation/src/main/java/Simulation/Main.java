package Simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;

import static Simulation.Util.Environment.getRateControlUrl;
import static Simulation.Util.Environment.getRateManagementUrl;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Rater Simulation");

        String raterManagement = getRateManagementUrl();
        String raterRateControl = getRateControlUrl();

        Simulator managementSimulator = new Simulator(raterManagement);
        managementSimulator.startSimulation();

        Simulator rateControlSimulator = new Simulator(raterRateControl);
        rateControlSimulator.startSimulation();
    }
}