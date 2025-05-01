package TokyoSubway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataLogger class logs simulation data, including queue lengths and wait times.
 */
public class DataLogger {
    private Map<Integer, Integer> queueLengths;
    private List<Double> waitTimes;

    public DataLogger() {
        this.queueLengths = new HashMap<>();
        this.waitTimes = new ArrayList<>();
    }

    public void logQueueLength(int time, int length) {
        queueLengths.put(time, length);
    }

    public void logWaitTime(double waitTime) {
        waitTimes.add(waitTime);
    }

    public Map<Integer, Integer> getQueueLengths() {
        return queueLengths;
    }

    public List<Double> getWaitTimes() {
        return waitTimes;
    }

    public double getAverageWaitTime() {
        if (waitTimes.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double time : waitTimes) {
            sum += time;
        }
        return sum / waitTimes.size();
    }

    public int getMaxQueueLength() {
        if (queueLengths.isEmpty()) return 0;
        return queueLengths.values().stream().max(Integer::compare).orElse(0);
    }
}