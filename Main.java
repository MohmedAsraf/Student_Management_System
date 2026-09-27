import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        // Load students from students.txt when the program starts
        manager.loadStudents();

        while (true) {

            System.out.println("\n==============================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter Student ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Student Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Course: ");
                String course = scanner.nextLine();

                System.out.print("Enter Email: ");
                String email = scanner.nextLine();

                Student student = new Student(
                        id,
                        name,
                        age,
                        course,
                        email
                );

                manager.addStudent(student);

                System.out.println("Student added successfully!");

            } else if (choice == 2) {

                manager.viewStudents();

            } else if (choice == 3) {

                System.out.print("Enter Student ID to search: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                Student student = manager.searchStudent(id);

                if (student != null) {

                    System.out.println("------------------------");
                    System.out.println("ID: " + student.getId());
                    System.out.println("Name: " + student.getName());
                    System.out.println("Age: " + student.getAge());
                    System.out.println("Course: " + student.getCourse());
                    System.out.println("Email: " + student.getEmail());
                    System.out.println("------------------------");

                } else {

                    System.out.println("Student not found.");
                }

            } else if (choice == 4) {

                System.out.print("Enter Student ID to update: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                manager.updateStudent(id, scanner);

            } else if (choice == 5) {

                System.out.print("Enter Student ID to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                manager.deleteStudent(id);

            } else if (choice == 6) {

                System.out.println("Thank you for using Student Management System!");
                break;

            } else {

                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
