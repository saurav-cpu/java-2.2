import java.io.*;
import java.util.*;

class Student implements Serializable {
    int studentID;
    String name;
    String grade;
    Student(int studentID, String name, String grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }
    public String toString() {
        return "StudentID: " + studentID + ", Name: " + name + ", Grade: " + grade;
    }
}

class Employee {
    int empId;
    String name;
    String designation;
    double salary;
    Employee(int empId, String name, String designation, double salary) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }
    public String toString() {
        return empId + " | " + name + " | " + designation + " | " + salary;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Part A: Sum of Integers (Autoboxing/Unboxing)");
            System.out.println("2. Part B: Serialize & Deserialize Student");
            System.out.println("3. Part C: Employee Management System");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: partA(); break;
                case 2: partB(); break;
                case 3: partC(); break;
                case 4: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
    static void partA() {
        System.out.println("Enter integers separated by space:");
        String input = sc.nextLine();
        String[] tokens = input.split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String s : tokens) {
            Integer num = Integer.parseInt(s);
            numbers.add(num);
        }
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        System.out.println("Sum = " + sum);
    }
    static void partB() {
        try {
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Grade: ");
            String grade = sc.nextLine();
            Student s1 = new Student(id, name, grade);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"));
            oos.writeObject(s1);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"));
            Student s2 = (Student) ois.readObject();
            ois.close();
            System.out.println("Deserialized Student: " + s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void partC() {
        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1: addEmployee(); break;
                case 2: displayEmployees(); break;
                case 3: return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
    static void addEmployee() {
        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String desig = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();
            Employee e = new Employee(id, name, desig, salary);
            FileWriter fw = new FileWriter("employees.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(e.toString());
            bw.newLine();
            bw.close();
            System.out.println("Employee Added Successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    static void displayEmployees() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("employees.txt"));
            String line;
            System.out.println("\n--- Employee Records ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("No employees found or file missing.");
        }
    }
}
