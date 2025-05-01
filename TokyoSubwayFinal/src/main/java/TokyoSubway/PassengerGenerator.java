package TokyoSubway;

import java.util.Random;

/**
 * PassengerGenerator class generates passenger arrivals based on an arrival rate.
 */
public class PassengerGenerator {
    private double arrivalRate; // Passengers per minute
    private Random random; // Random number generator

    public PassengerGenerator(double arrivalRate) {
        this.arrivalRate = arrivalRate;
        this.random = new Random();
    }

    /**
     * Generates the number of passengers arriving in a 1-minute interval.
     * @return Number of passengers arriving in this minute.
     */
    public int generatePassengers() {
        int passengers = (int) Math.floor(arrivalRate);
        double fractional = arrivalRate - passengers;
        if (random.nextDouble() < fractional) {
            passengers++;
        }
        return passengers;
    }
}