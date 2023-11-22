package Simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Simulation.Util.Environment.getRateControlUrl;
import static Simulation.Util.Environment.getRateManagementUrl;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("Rater Simulation");

        String raterManagement = getRateManagementUrl();
        String raterRateControl = getRateControlUrl();

//        RateControlSimulator managementSimulator = new RateControlSimulator(raterManagement);
//        managementSimulator.startSimulation();

        RateControlSimulator rateControlSimulator = new RateControlSimulator(raterRateControl);
        rateControlSimulator.startSimulation();
    }
}