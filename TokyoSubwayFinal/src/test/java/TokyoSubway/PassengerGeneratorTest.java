package TokyoSubway;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * PassengerGeneratorTest class contains unit tests for the PassengerGenerator class.
 */
@SuppressWarnings("unused")
public class PassengerGeneratorTest {

    @Test
    public void testGeneratePassengerNoArrival() {
        PassengerGenerator generator = new PassengerGenerator(0.0);
        int passengers = generator.generatePassengers();
        assertEquals("No passengers should arrive with arrivalRate=0", 0, passengers);
    }

    @Test
    public void testGeneratePassengerAlwaysArrive() {
        PassengerGenerator generator = new PassengerGenerator(100.0);
        int passengers = generator.generatePassengers();
        assertTrue("Should generate many passengers with arrivalRate=100", passengers >= 100);
    }
}
