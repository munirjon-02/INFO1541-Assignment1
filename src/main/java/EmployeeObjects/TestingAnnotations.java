package EmployeeObjects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

public class TestingAnnotations {
    public static void main(String[] args) throws Exception {

        // Create employees
        HourlyEmployee hourly = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);
        SalaryEmployee salary = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);
        CommissionEmployee commission = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", .0265);

        Object[] employees = {hourly, salary, commission};
        int employeeTypeCount = 0;

        DecimalFormat df = new DecimalFormat("#0.00");

        for (Object emp : employees) {
            Class<?> cls = emp.getClass();

            // Count classes with EmployeeType annotation
            if (cls.isAnnotationPresent(EmployeeType.class)) {
                employeeTypeCount++;
            }

            // Check fields for PayRate annotation
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(PayRate.class)) {
                    field.setAccessible(true);
                    Object value = field.get(emp);
                    System.out.println(cls.getSimpleName() + " pay rate: $" + df.format(value));
                }
            }

            // Check methods for WeeklyPayCalculator annotation
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(WeeklyPayCalculator.class)) {
                    method.setAccessible(true);
                    Object weeklyPay = method.invoke(emp);
                    System.out.println(cls.getSimpleName() + " weekly pay: $" + df.format(weeklyPay));
                }
            }

            System.out.println("------------------------");
        }

        System.out.println("You have " + employeeTypeCount + " employee types annotated.");
    }
}