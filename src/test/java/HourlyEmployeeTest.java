import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {
    HourlyEmployee emp = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);

    @Test
    void testIncreaseHours() {
        emp.increaseHours(35);
        emp.increaseHours(-5); // should be ignored
        assertEquals(35, emp.getHoursWorked());
    }

    @Test
    void testAnnualRaise() {
        emp.annualRaise();
        assertEquals(34.49, emp.getWage(), 0.01);
    }

    @Test
    void testCalculateWeeklyPayNoOvertime() {
        emp.increaseHours(35);
        assertEquals(1149.75, emp.calculateWeeklyPay(), 0.01);
    }

    @Test
    void testCalculateWeeklyPayWithOvertime() {
        emp.increaseHours(45);
        assertEquals(1560.38, emp.calculateWeeklyPay(), 0.01);
    }
}