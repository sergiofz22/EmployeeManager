import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private List<Employee> employees;

    // Constructor    
    public EmployeeManager() {
        this.employees = new ArrayList<>();
    }

    // Add an employee
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully.");
    }

    // Remove an employee
    public boolean removeEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                employees.remove(i);
                System.out.println("Employee removed successfully.");
                return true; // Exit after removing
            }
        }
        System.out.println("Employee not found.");
        return false; // If the employee is not found
    }

    // List all employees
    public List<Employee> listEmployees() {
        return employees; // Return the full list
    }

    // Find an employee by ID
    public Employee findEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        System.out.println("Employee not found.");
        return null;
    }
}

