package EmployeeBlueprints;

import java.util.ArrayList;

/**
 * The base employee class for the system. Holds the default information.
 *
 * @author Lucas Hartman
 * @version 1.0.1
 */
public abstract class Employee implements Printable {
    private String firstName;
    private String lastName;
    private int employeeNum;
    private String department;
    private String jobTitle;
    private EmployeeType employeeType;
    private ArrayList<EmergencyContact> emergencyContacts;

    /**
     * Main constructor that takes on all the basic information.
     * @param fn String for the first name
     * @param ln String for the last name
     * @param en integer for the employee number
     * @param dept String for the department they work in
     * @param job String for the job title they hold
     * @param et EmployeeObjects.EmployeeType value for the type of employee they are.
     */
    public Employee(String fn, String ln, int en, String dept, String job, EmployeeType et)
    {
        firstName = fn;
        lastName = ln;
        employeeNum = en;
        department = dept;
        jobTitle = job;
        emergencyContacts = new ArrayList<EmergencyContact>();
        Printable.percent.setMaximumFractionDigits(3);
        employeeType = et;
    }

    /**
     * This is a second constructor that should be used to set up a new employee
     * if they don't have a department or job title yet.
     * @param fn String for the first name
     * @param ln String for the last name
     * @param en integer for the employee number
     * @param et EmployeeObjects.EmployeeType value for the type of employee they are.
     */
    public Employee(String fn, String ln, int en, EmployeeType et)
    {
        firstName = fn;
        lastName = ln;
        employeeNum = en;
        department = "";
        jobTitle = "";
        emergencyContacts = new ArrayList<EmergencyContact>();
        Printable.percent.setMaximumFractionDigits(3);
        employeeType = et;
    }

    /**
     * A copy constructor that copies one employee to a new one.
     * @param e Employee object to copy from.
     */
    public Employee(Employee e)
    {
        firstName = e.getFirstName();
        lastName = e.getLastName();
        employeeNum = e.getEmployeeNumber();
        department = e.getDepartment();
        jobTitle = e.getJobTitle();
        emergencyContacts = new ArrayList<EmergencyContact>();
        employeeType = e.getEmployeeType();
    }

    /**
     * The default blank constructor.
     */
    public Employee()
    {
        firstName = "";
        lastName = "";
        employeeNum = 0;
        department = "";
        jobTitle = "";
        emergencyContacts = new ArrayList<EmergencyContact>();
        Printable.percent.setMaximumFractionDigits(3);
        employeeType = EmployeeType.HOURLY;
    }

    /**
     * Returns the employee type.
     * @return EmployeeObjects.EmployeeType of the employee
     */
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    /**
     * Updates the employee type.
     * @param et EmployeeObjects.EmployeeType for the update
     */
    public void setEmployeeType(EmployeeType et)
    {
        employeeType = et;
    }

    /**
     * Returns the first name of the employee
     * @return String for the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the employee
     * @return String for the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the employee number of the employee
     * @return int for the employee number
     */
    public int getEmployeeNumber() {
        return employeeNum;
    }

    /**
     * Returns the department the employee works in.
     * @return String for the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the job title of the employee
     * @return String for the job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Changes the employee number of the employee
     * @param empNum int for the new employee number
     */
    public void setEmployeeNumber(int empNum)
    {
        employeeNum = empNum;
    }

    /**
     * Changes the first name of the employee
     * @param firstName String for the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Changes the last name of the employee
     * @param lastName String for the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Changes the department of the employee
     * @param department String for the new department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Changes the job title of the employee
     * @param jobTitle String for the new job title
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Abstract method for calculating an employee's weekly pay
      * @return double value for the weekly pay
     */
    public abstract double calculateWeeklyPay();

    /**
     * Abstract method that will reset values on employees to start a new week.
     */
    public abstract void resetWeek();

    /**
     * Abstract method that gives the employee's an annual raise
     */
    public abstract void annualRaise();

    /**
     * Abstract mehtod that gives the employee's a holiday bonus
     * @return double value of their bonus pay
     */
    public abstract double holidayBonus();

    /**
     * Abstract method that updates the pay of each employee.
     * @param pay double for the new pay value
     */
    public abstract void setPay(double pay);

    /**
     * Static method that returns a currency formatted value of the parameter sent.
     * @param amt double value to format
     * @return String currency formatted value of the parameter.
     */
    public static String currencyFormat(double amt)
    {
        return Printable.currency.format(amt);
    }

    /**
     * Prints out a formatted look of the Employee
     * @return String value of the employee
     */
    @Override
    public String toString()
    {
        return "Name: " + firstName +" " + lastName + "\nID: " + employeeNum +
                "\nDepartment: " + department + "\nTitle: " + jobTitle +
                "\nType: " + employeeType.toString();
    }


    /**
     * Interface implemented method.
     */
    public void print()
    {
        System.out.println(toString());
    }

    /**
     * Prints out the emergency contacts of the employee
     */
    public void printEmergencyContacts()
    {
        System.out.println("**** Emergency Contacts ****\n");
        for (int i = 0; i < emergencyContacts.size(); i++)
        {
            System.out.println("**** Contact "+ (i+1) + "****");
            emergencyContacts.get(i).printContact();
            System.out.println(); // add a space between each
        }
    }

    /**
     * clears the emergency contact list of the employees.
     */
    public void clearContacts()
    {
        emergencyContacts.clear();
    }

    /**
     * Adds a new emergency contact for the employee
     * @param contact EmergencyContact for a new contact
     */
    public void addNewContact(EmergencyContact contact)
    {
        emergencyContacts.add(contact);
    }

    /**
     * Returns the emergencyContact list.
     * @return ArrayList of Emergency Contacts
     */
    public ArrayList<EmergencyContact> getEmergencyContactList() {
        return emergencyContacts;
    }

    /**
     * Removes an emergency contact
     * @param contact EmergencyContact to remove from list
     * @return true if successful, false otherwise
     */
    public boolean removeContact(EmergencyContact contact)
    {
        if (emergencyContacts.contains(contact))
        {
            emergencyContacts.remove(contact);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Removes an emergency contact via index
     * @param index int for the index location of contact
     * @return true if successful, false otherwise
     */
    public boolean removeContact(int index)
    {
        if (emergencyContacts.size() > index && emergencyContacts.size() > 0)
        {
            emergencyContacts.remove(index);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Compares to another employee to see if they are the same. Compares via employee number.
     * @param obj2 Object that is the second employee
     * @return true if same employee, false otherwise.
     */
    public boolean equals(Object obj2)
    {
        if (obj2 instanceof Employee)
        {
            Employee e2 = (Employee)obj2;
            if (employeeNum == e2.getEmployeeNumber())
            {
                return true;
            }
        }
        return false;
    }

}
