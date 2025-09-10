import EmployeeObjects.*;


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
                System.out.println(clazz.getSimpleName() + " EmployeeObjects.EmployeeType: " + type.type());
                annotatedClasses++;
            }

            // Field-level annotation
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(PayRate.class)) {
                    field.setAccessible(true);
                    PayRate payRate = field.getAnnotation(PayRate.class);
                    try {
                        System.out.println(clazz.getSimpleName() + " pay rate (" + payRate.type() + "): $" + field.get(emp));
                    } catch (IllegalAccessException e) {
                        System.err.println("Cannot access field " + field.getName() + " in " + clazz.getSimpleName());
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
                        System.err.println("Cannot invoke method " + method.getName() + " in " + clazz.getSimpleName());
                    }
                }
            }

            System.out.println("----------------------------");
        }

        System.out.println("You have " + annotatedClasses + " employee types.");
    }
}