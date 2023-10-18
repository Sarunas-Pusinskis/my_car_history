package lt.sarunas.Car.repository.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarHistoryTest {

    @Test
    public void testConstructorWithPartName() {
        String partName = "Part 1";
        CarHistory carHistory = new CarHistory(partName);

        assertEquals(partName, carHistory.getPart_Name());
    }
}

