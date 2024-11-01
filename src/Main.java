// Hao Phong Le - N01605830
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an object for Employee class
        Employee employeeInfo = new Employee();

        // Set up scanner allow user to input employee information
        Scanner input = new Scanner(System.in);

        // While loop for continuous input
        while (true) {
            System.out.println("Please en9ter the info of the employee");

            // Reading employee details
            System.out.print("Enter Employee ID: ");
            int employeeId = input.nextInt();
            input.nextLine();

            System.out.print("Enter Employee's First Name: ");
            String firstName = input.nextLine();

            System.out.print("Enter Employee's Last Name: ");
            String lastName = input.nextLine();

            // Update object's fields using setters
            employeeInfo.setEmployeeId(employeeId);
            employeeInfo.setFirstName(firstName);
            employeeInfo.setLastName(lastName);

            // Print employee information to the console
            System.out.println("\nEmployee Details:");
            System.out.println("Employee ID: " + employeeInfo.getEmployeeId());
            System.out.println("First Name: " + employeeInfo.getFirstName());
            System.out.println("Last Name: " + employeeInfo.getLastName());
            System.out.println("------------------------------");

            // Stop the program if user type 'exit'
            System.out.print("Press Enter to continue or type 'exit' to quit: ");
            if (input.nextLine().equalsIgnoreCase("exit")) {
                System.out.println("See you again!");
                break;
            }
        }
        input.close();  // Close the scanner
    }
}

class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    

    public String getLastName() {
        return lastName;
    }

    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
