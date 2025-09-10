package EmployeeObjects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class TestingAnnotations {
    public static void main(String[] args) throws Exception {

        // create instances (replace constructors if yours differ)
        HourlyEmployee hourly = new HourlyEmployee("Tony", "Stark", 5749, "Service", "Lead Service Manager", 32.85);
        SalaryEmployee salary = new SalaryEmployee("Steve", "Rodgers", 3781, "Sales", "Manager", 64325);
        CommissionEmployee commission = new CommissionEmployee("Clint", "Barton", 6847, "Sales", "Customer Representative", .0265);

        // IMPORTANT: set non-default values so weekly pay returns meaningful numbers
        hourly.increaseHours(35);       // now hourly.calculateWeeklyPay() will be 32.85 * 35 = 1149.75
        commission.increaseSales(1000); // now commission.calculateWeeklyPay() will be rate * 1000

        Object[] employees = {hourly, salary, commission};
        DecimalFormat df = new DecimalFormat("#0.00");
        int employeeTypeCount = 0;

        for (Object emp : employees) {
            Class<?> cls = emp.getClass();
            System.out.println(cls.getSimpleName() + ":");

            // Class annotation count
            EmployeeType et = cls.getAnnotation(EmployeeType.class);
            if (et != null) {
                employeeTypeCount++;
                System.out.println("  EmployeeType: " + et.type());
            } else {
                System.out.println("  EmployeeType: <missing>");
            }

            // Field annotated with @PayRate
            boolean payRateFound = false;
            for (Field field : cls.getDeclaredFields()) {
                PayRate pr = field.getAnnotation(PayRate.class);
                if (pr != null) {
                    field.setAccessible(true);
                    double value = toDouble(field.get(emp));
                    // If value is zero, try alternative getters (in case annotation was placed on a different field)
                    if (isZero(value)) {
                        Double alt = tryAlternativeGetters(cls, emp);
                        if (alt != null) value = alt;
                    }
                    System.out.println("  Employee pay rate: $" + df.format(value) + "  (annotation type=" + pr.type() + ")");
                    payRateFound = true;
                    break;
                }
            }
            if (!payRateFound) {
                System.out.println("  Employee pay rate: <no @PayRate field found>");
            }

            // Find and invoke method annotated with @WeeklyPayCalculator
            Method wpc = findAnnotatedMethod(cls, WeeklyPayCalculator.class);
            if (wpc != null) {
                wpc.setAccessible(true);
                Object weekly = wpc.invoke(emp);
                System.out.println("  Weekly pay: $" + df.format(toDouble(weekly)));
            } else {
                System.out.println("  Weekly pay: <no @WeeklyPayCalculator method found>");
            }

            System.out.println("------------------------");
        }

        System.out.println("You have " + employeeTypeCount + " employee types annotated.");
    }

    private static Method findAnnotatedMethod(Class<?> cls, Class annotationClass) {
        // check declared methods first, then public/inherited methods
        for (Method m : cls.getDeclaredMethods()) {
            if (m.isAnnotationPresent(annotationClass)) return m;
        }
        for (Method m : cls.getMethods()) {
            if (m.isAnnotationPresent(annotationClass)) return m;
        }
        return null;
    }

    private static Double tryAlternativeGetters(Class<?> cls, Object emp) {
        List<String> getters = Arrays.asList(
                "getWage", "getSalary", "getRate", "getPay", "getPayRate", "getHourlyRate", "getCommissionRate", "getSales"
        );
        for (String g : getters) {
            try {
                Method gm = cls.getMethod(g);
                Object v = gm.invoke(emp);
                double d = toDouble(v);
                if (!isZero(d)) return d;
            } catch (NoSuchMethodException e) {
                // ignore - try next getter
            } catch (Exception e) {
                // ignore other reflection exceptions
            }
        }
        return null;
    }

    private static boolean isZero(double d) {
        return Math.abs(d) < 0.000001;
    }

    private static double toDouble(Object val) {
        if (val == null) return 0.0;
        if (val instanceof Number) return ((Number) val).doubleValue();
        try {
            return Double.parseDouble(val.toString());
        } catch (Exception e) {
            return 0.0;
        }
    }
}