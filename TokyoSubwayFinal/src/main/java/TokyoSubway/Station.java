package TokyoSubway;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a subway station holding waiting passengers.
 */
public class Station {
    private Queue<Double> passengerQueue;

    public Station() {
        this.passengerQueue = new LinkedList<>();
    }

    public void addPassenger(double arrivalTime) {
        passengerQueue.offer(arrivalTime);
    }

    public int getQueueSize() {
        return passengerQueue.size();
    }

    public int boardPassengers(int count, Train train, DataLogger logger, double currentTime) {
        int boarded = 0;
        while (boarded < count && !passengerQueue.isEmpty()) {
            double arrivalTime = passengerQueue.poll();
            double waitTime = currentTime - arrivalTime;
            logger.logWaitTime(waitTime);
            boarded++;
        }
        return boarded;
    }
}

