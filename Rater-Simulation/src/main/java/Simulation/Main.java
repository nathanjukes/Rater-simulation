package Simulation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Simulation.Util.Environment.getRateControlUrl;
import static Simulation.Util.Environment.getRateManagementUrl;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("Rater Simulation");

        String raterRateControl = getRateControlUrl();

        RateControlSimulator rateControlSimulator = new RateControlSimulator(raterRateControl);
        rateControlSimulator.startSimulation(10);
        rateControlSimulator.startSimulation(50);
        rateControlSimulator.startSimulation(100);
        rateControlSimulator.startSimulation(500);
        rateControlSimulator.startSimulation(1000);
    }
}