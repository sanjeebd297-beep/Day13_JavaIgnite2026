/*
Create a program that stores employee data in memory:

Each employee has:

id
department
salary
Task:
Store employees using ArrayList
Create a HashMap where:
Key = Department
Value = Total salary of that department
Display department with highest total salary
Hint:

Break into:

Grouping logic
Summation logic
Comparison logic
*/
package JavaIgniteDay13;
import java.util.ArrayList;
import java.util.HashMap;

class Employees {
    int id;
    String department;
    double salary;

    Employees(int id, String department, double salary) {
        this.id = id;
        this.department = department;
        this.salary = salary;
    }
}

public class EmpDataAnalyzer {
	
	public static void main(String[] args) {
    	// TODO Auto-generated method stub
        // Store employees in ArrayList
        ArrayList<Employees> employees = new ArrayList<>();

        employees.add(new Employees(101, "IT", 50000));
        employees.add(new Employees(102, "HR", 30000));
        employees.add(new Employees(103, "IT", 40000));
        employees.add(new Employees(104, "Sales", 60000));
        employees.add(new Employees(105, "HR", 25000));
        employees.add(new Employees(106, "HR", 35000));
        employees.add(new Employees(107, "Sales", 40000));

        // Grouping + Summation Logic
        HashMap<String, Double> departmentSalary = new HashMap<>();

        for (Employees emp : employees) {

            String dept = emp.department;
            double salary = emp.salary;

            if (departmentSalary.containsKey(dept)) {
                departmentSalary.put(
                        dept,
                        departmentSalary.get(dept) + salary
                );
            } else {
                departmentSalary.put(dept, salary);
            }
        }

        // Display department-wise total salary
        System.out.println("Department Salary Totals:");

        for (String dept : departmentSalary.keySet()) {
            System.out.println(dept + " -> Rs." + departmentSalary.get(dept));
        }

        // Comparison Logic
        String highestDepartment = "";
        double highestSalary = 0;

        for (String dept : departmentSalary.keySet()) {

            if (departmentSalary.get(dept) > highestSalary) {
                highestSalary = departmentSalary.get(dept);
                highestDepartment = dept;
            }
        }

        System.out.println("\nDepartment with Highest Total Salary:");
        System.out.println(highestDepartment + " -> Rs." + highestSalary);
    }
}
