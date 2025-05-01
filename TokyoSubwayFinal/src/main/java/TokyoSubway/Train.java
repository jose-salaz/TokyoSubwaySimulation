package TokyoSubway;

/**
 * Train class manages train operations in the Tokyo Subway simulation.
 */
public class Train {
    private int rejectedPassengers;
    private static final int TRAIN_CAPACITY = 10;

    public Train() {
        this.rejectedPassengers = 0;
    }

    public int boardPassengers(int queueSize) {
        return Math.min(queueSize, TRAIN_CAPACITY);
    }

    public void rejectPassenger(int count) {
        rejectedPassengers += count;
    }


    public int getRejectedPassengers() {
        return rejectedPassengers;
    }
}