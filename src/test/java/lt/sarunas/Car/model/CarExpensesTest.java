package lt.sarunas.Car.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarExpensesTest {

    @Test
    public void testGetTotalPartsPrice() {
        CarExpenses carExpenses = new CarExpenses();
        carExpenses.setTotalPartsPrice(100.0);
        assertEquals(100.0, carExpenses.getTotalPartsPrice());
    }

    @Test
    public void testGetTotalRepairPrice() {
        CarExpenses carExpenses = new CarExpenses();
        carExpenses.setTotalRepairPrice(50.0);
        assertEquals(50.0, carExpenses.getTotalRepairPrice());
    }
}