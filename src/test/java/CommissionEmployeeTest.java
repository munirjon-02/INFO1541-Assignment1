import EmployeeObjects.CommissionEmployee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommissionEmployeeTest {
    CommissionEmployee emp = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", 0.0265);

    @Test
    void testIncreaseSales() {
        emp.increaseSales(5000);
        emp.increaseSales(-2000); // should be ignored
        assertEquals(5000, emp.getSales(), 0.01);
    }

    @Test
    void testHolidayBonus() {
        assertEquals(0.0, emp.holidayBonus(), 0.01);
    }

    @Test
    void testAnnualRaise() {
        emp.annualRaise();
        emp.annualRaise();
        assertEquals(0.0305, emp.getRate(), 0.0001);
    }
}