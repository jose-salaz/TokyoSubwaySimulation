package TokyoSubway;

public class SubwaySimulationTest {
    public static void main(String[] args) {
        System.out.println("Running Baseline Scenario (trainFreq=6.0, arrivalRate=2.0):");
        runScenario(6.0, 2.0, 60.0, 1);

        System.out.println("\nRunning Optimistic Scenario (trainFreq=4.0, arrivalRate=1.0):");
        runScenario(4.0, 1.0, 60.0, 1);

        System.out.println("\nRunning Pessimistic Scenario (trainFreq=8.0, arrivalRate=3.0):");
        runScenario(8.0, 3.0, 60.0, 1);
    }

    private static void runScenario(double trainFreq, double arrivalRate, double duration, int numRuns) {
        double totalWaitTime = 0.0;
        int totalMaxQueue = 0;
        int totalRejections = 0;

        for (int i = 0; i < numRuns; i++) {
            Train train = new Train();
            Station station = new Station();
            PassengerGenerator generator = new PassengerGenerator(arrivalRate);
            DataLogger logger = new DataLogger();
            double currentTime = 0.0;

            for (int t = 0; t < (int) duration; t++) {
                currentTime = t;
                int newPassengers = generator.generatePassengers();
                for (int p = 0; p < newPassengers; p++) {
                    station.addPassenger(currentTime);
                }

                if (t % (int) trainFreq == 0) {
                    int queueSize = station.getQueueSize();
                    int boarded = train.boardPassengers(queueSize);
                    station.boardPassengers(boarded, train, logger, currentTime);
                }

                logger.logQueueLength(t, station.getQueueSize());
            }

            totalWaitTime += logger.getAverageWaitTime();
            totalMaxQueue += logger.getMaxQueueLength();
            totalRejections += train.getRejectedPassengers(); // likely 0 in current design
        }

        System.out.println("Simulation Results (Averaged over " + numRuns + " runs):");
        System.out.printf("Average Wait Time: %.2f minutes%n", totalWaitTime / numRuns);
        System.out.printf("Maximum Queue Length: %.2f%n", (double) totalMaxQueue / numRuns);
        System.out.printf("Rejections: %.2f%n", (double) totalRejections / numRuns);
    }
}
