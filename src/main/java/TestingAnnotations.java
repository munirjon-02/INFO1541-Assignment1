import EmployeeObjects.CommissionEmployee;
import EmployeeObjects.HourlyEmployee;
import EmployeeObjects.SalaryEmployee;

import java.lang.reflect.*;

public class TestingAnnotations {
    public static void main(String[] args) throws Exception {
        HourlyEmployee h = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);
        SalaryEmployee s = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);
        CommissionEmployee c = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Rep", 0.0265);

        Object[] employees = {h, s, c};
        int annotatedCount = 0;

        for (Object emp : employees) {
            Class<?> cls = emp.getClass();

            if (cls.isAnnotationPresent(EmployeeType.class)) {
                annotatedCount++;
            }

            // Fields
            for (Field f : cls.getDeclaredFields()) {
                if (f.isAnnotationPresent(PayRate.class)) {
                    f.setAccessible(true);
                    System.out.println(cls.getSimpleName() + " pay rate: $" + f.get(emp));
                }
            }

            // Methods
            for (Method m : cls.getDeclaredMethods()) {
                if (m.isAnnotationPresent(WeeklyPayCalculator.class)) {
                    m.setAccessible(true);
                    System.out.println(cls.getSimpleName() + " weekly pay: " + m.invoke(emp));
                }
            }
        }

        System.out.println("You have " + annotatedCount + " employee types");
    }
}