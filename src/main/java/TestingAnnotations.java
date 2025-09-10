import EmployeeObjects.CommissionEmployee;
import EmployeeObjects.HourlyEmployee;
import EmployeeObjects.SalaryEmployee;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestingAnnotations {
    public static void main(String[] args) {
        // Create employee objects
        HourlyEmployee hourly = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);
        SalaryEmployee salary = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);
        CommissionEmployee commission = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Rep", 0.0265);

        Object[] employees = {hourly, salary, commission};
        int annotatedClasses = 0;

        for (Object emp : employees) {
            Class<?> clazz = emp.getClass();

            // Class-level annotation
            if (clazz.isAnnotationPresent(EmployeeType.class)) {
                EmployeeType type = clazz.getAnnotation(EmployeeType.class);
                System.out.println(clazz.getSimpleName() + " EmployeeType: " + type.type());
                annotatedClasses++;
            }

            // Field-level annotation
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(PayRate.class)) {
                    field.setAccessible(true);
                    try {
                        PayRate payRate = field.getAnnotation(PayRate.class);
                        System.out.println(clazz.getSimpleName() + " pay rate (" + payRate.type() + "): $" + field.get(emp));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Method-level annotation
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(WeeklyPayCalculator.class)) {
                    method.setAccessible(true);
                    try {
                        Object result = method.invoke(emp);
                        System.out.println(clazz.getSimpleName() + " weekly pay: " + result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("----------------------------");
        }

        System.out.println("You have " + annotatedClasses + " employee types annotated.");
    }
}