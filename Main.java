import java.util.List;
import java.util.Scanner;

public class Main {
    private static StudentManager manager = new StudentManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loginMenu();
    }

    private static void loginMenu() {
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Login as Owner");
            System.out.println("2. Login as User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        if (authenticateOwner()) ownerMenu();
                        break;
                    case 2:
                        userMenu();
                        break;
                    case 3:
                        System.out.println("Exiting system...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static boolean authenticateOwner() {
        System.out.print("Enter owner password: ");
        String password = scanner.nextLine();
        if (password.equals("admin123")) {
            return true;
        } else {
            System.out.println("Incorrect password!");
            return false;
        }
    }

    private static void ownerMenu() {
        while (true) {
            System.out.println("\n===== OWNER MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: addStudentUI(); break;
                    case 2: deleteStudentUI(); break;
                    case 3: searchStudentUI(); break;
                    case 4: updateStudentUI(); break;
                    case 5: displayAllStudentsUI(); break;
                    case 6: System.out.println("Logging out..."); return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. Search Student");
            System.out.println("2. Logout");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: 
                        searchStudentUI(); 
                        break;
                    case 2: 
                        System.out.println("Logging out..."); 
                        return;
                    default: 
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private static void addStudentUI() {
        System.out.println("\n--- Add New Student ---");

        // 1. Name Validation (Letters only)
        String name;
        while (true) {
            System.out.print("Enter student name (Letters only): ");
            name = scanner.nextLine().trim();
            if (name.matches("^[a-zA-Z\\s]+$")) {
                break;
            }
            System.out.println("Error: Name can only contain letters and spaces (No numbers allowed).");
        }

        // 2. ID Validation (Alphanumeric/Characters)
        String id;
        while (true) {
            System.out.print("Enter student ID (Characters/Numbers allowed): ");
            id = scanner.nextLine().trim();
            if (!id.isEmpty() && manager.isIdUnique(id)) {
                break;
            }
            if (id.isEmpty()) {
                System.out.println("Error: ID cannot be empty.");
            } else {
                System.out.println("Error: This ID already exists. Try a unique ID.");
            }
        }

        // 3. Sex Validation (M/F/O)
        String sex;
        while (true) {
            System.out.print("Enter student sex (M/F/O): ");
            sex = scanner.nextLine().trim();
            if (sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F") || sex.equalsIgnoreCase("O")) {
                break;
            }
            System.out.println("Error: Please enter M, F, or O.");
        }

        // 4. Course Validation (Letters only)
        String course;
        while (true) {
            System.out.print("Enter course (Letters only): ");
            course = scanner.nextLine().trim();
            if (course.matches("^[a-zA-Z\\s]+$")) {
                break;
            }
            System.out.println("Error: Course name can only contain letters and spaces (No numbers allowed).");
        }

        // 5. Grade Validation
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine().trim();

        // 6. Department Validation (Letters only)
        String department;
        while (true) {
            System.out.print("Enter department (Letters only): ");
            department = scanner.nextLine().trim();
            if (department.matches("^[a-zA-Z\\s]+$")) {
                break;
            }
            System.out.println("Error: Department name must contain letters only.");
        }

        // 7. Age Validation (Integer number)
        int age = 0;
        while (true) {
            try {
                System.out.print("Enter age (Integer number only): ");
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age > 0 && age < 120) {
                    break;
                }
                System.out.println("Error: Please enter a valid age between 1 and 120.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Age must be a whole number.");
            }
        }

        Student newStudent = new Student(name, id, sex, course, grade, department, age);
        manager.addStudent(newStudent);
        System.out.println("Student added successfully!");
    }

    private static void deleteStudentUI() {
        System.out.print("\nEnter student ID to delete: ");
        String id = scanner.nextLine().trim();
        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void searchStudentUI() {
        System.out.println("\n--- Search Student ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Choice: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter search term: ");
            String term = scanner.nextLine().trim();

            List<Student> results = manager.searchStudents(choice, term);
            if (results.isEmpty()) {
                System.out.println("No matching records found.");
            } else {
                for (Student s : results) {
                    System.out.println(s);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
        }
    }

    private static void updateStudentUI() {
        System.out.print("\nEnter student ID to update: ");
        String id = scanner.nextLine().trim();
        Student student = manager.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Current Info: " + student);
        System.out.println("Enter new details (press Enter to keep current value):");

        // Name update validation
        System.out.print("Name [" + student.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            if (name.matches("^[a-zA-Z\\s]+$")) {
                student.setName(name);
            } else {
                System.out.println("Error: Invalid name format. Name not updated.");
            }
        }

        System.out.print("Sex [" + student.getSex() + "]: ");
        String sex = scanner.nextLine().trim();
        if (!sex.isEmpty()) student.setSex(sex);

        // Course update validation
        System.out.print("Course [" + student.getCourse() + "]: ");
        String course = scanner.nextLine().trim();
        if (!course.isEmpty()) {
            if (course.matches("^[a-zA-Z\\s]+$")) {
                student.setCourse(course);
            } else {
                System.out.println("Error: Invalid course format. Not updated.");
            }
        }

        System.out.print("Grade [" + student.getGrade() + "]: ");
        String grade = scanner.nextLine().trim();
        if (!grade.isEmpty()) student.setGrade(grade);

        // Department update validation
        System.out.print("Department [" + student.getDepartment() + "]: ");
        String dept = scanner.nextLine().trim();
        if (!dept.isEmpty()) {
            if (dept.matches("^[a-zA-Z\\s]+$")) {
                student.setDepartment(dept);
            } else {
                System.out.println("Error: Invalid department format. Not updated.");
            }
        }

        System.out.print("Age [" + student.getAge() + "]: ");
        String ageStr = scanner.nextLine().trim();
        if (!ageStr.isEmpty()) {
            try {
                int age = Integer.parseInt(ageStr);
                if (age > 0 && age < 120) {
                    student.setAge(age);
                } else {
                    System.out.println("Invalid age range, left unchanged.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input, left unchanged.");
            }
        }

        System.out.println("Student updated successfully!");
    }

    private static void displayAllStudentsUI() {
        List<Student> students = manager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("\nNo students to display.");
            return;
        }

        System.out.println("\n----------------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-8s %-10s %-10s %-10s %-5s\n",
                "No.", "Name", "ID", "Sex", "Course", "Grade", "Department", "Age");
        System.out.println("----------------------------------------------------------------------------------");

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%-5d %-15s %-10s %-8s %-10s %-10s %-10s %-5d\n",
                    (i + 1), s.getName(), s.getId(), s.getSex(), s.getCourse(), s.getGrade(), s.getDepartment(), s.getAge());
        }
    }
}