package TokyoSubway;

import java.util.ArrayList;
import java.util.List;

/**
 * SubwaySimulation class models a single-station Tokyo subway system.
 */
public class SubwaySimulation {
    private double trainFreq;
    private double arrivalRate;
    private DataLogger logger;
    private Train train;
    private List<Double> passengerArrivalTimes;
    private double currentTime;

    public SubwaySimulation(double trainFreq, double arrivalRate) {
        this.trainFreq = trainFreq;
        this.arrivalRate = arrivalRate;
        this.logger = new DataLogger();
        this.train = new Train();
        this.passengerArrivalTimes = new ArrayList<>();
        this.currentTime = 0.0;
    }

    private void runSingle(double duration) {
        PassengerGenerator generator = new PassengerGenerator(arrivalRate);
        int nextTrainTime = (int) trainFreq;

        for (int t = 0; t < (int) duration; t++) {
            currentTime = t;
            int newPassengers = generator.generatePassengers();
            for (int i = 0; i < newPassengers; i++) {
                passengerArrivalTimes.add(currentTime);
            }

            if (t % (int) trainFreq == 0) {
                int queueSize = passengerArrivalTimes.size();
                if (queueSize > 0) {
                    int boarded = train.boardPassengers(queueSize);
                    for (int i = 0; i < boarded; i++) {
                        double arrivalTime = passengerArrivalTimes.get(i);
                        double waitTime = currentTime - arrivalTime;
                        logger.logWaitTime(waitTime);
                    }
                    passengerArrivalTimes.subList(0, boarded).clear();
                }
                nextTrainTime += (int) trainFreq;
            }

            logger.logQueueLength(t, passengerArrivalTimes.size());
        }
    }

    public void run(double duration, int numRuns) {
        double totalWaitTime = 0.0;
        int totalMaxQueue = 0;
        int totalRejections = 0;

        for (int i = 0; i < numRuns; i++) {
            this.logger = new DataLogger();
            this.train = new Train();
            this.passengerArrivalTimes = new ArrayList<>();
            this.currentTime = 0.0;

            runSingle(duration);

            totalWaitTime += logger.getAverageWaitTime();
            totalMaxQueue += logger.getMaxQueueLength();
            totalRejections += train.getRejectedPassengers();
        }

        System.out.println("Simulation Results (Averaged over " + numRuns + " runs):");
        System.out.printf("Average Wait Time: %.2f minutes%n", totalWaitTime / numRuns);
        System.out.printf("Maximum Queue Length: %.2f%n", (double) totalMaxQueue / numRuns);
        System.out.printf("Rejections: %.2f%n", (double) totalRejections / numRuns);
    }

    public DataLogger getLogger() {
        return logger;
    }

    public Train getTrain() {
        return train;
    }

    public static void main(String[] args) {
        System.out.println("Running Baseline Scenario (trainFreq=6.0, arrivalRate=2.0):");
        SubwaySimulation baseline = new SubwaySimulation(6.0, 2.0);
        baseline.run(60.0, 100);

        System.out.println("\nRunning Optimistic Scenario (trainFreq=4.0, arrivalRate=1.0):");
        SubwaySimulation optimistic = new SubwaySimulation(4.0, 1.0);
        optimistic.run(60.0, 100);

        System.out.println("\nRunning Pessimistic Scenario (trainFreq=8.0, arrivalRate=3.0):");
        SubwaySimulation pessimistic = new SubwaySimulation(8.0, 3.0);
        pessimistic.run(60.0, 100);
    }
}
