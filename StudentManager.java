import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {

    private ArrayList<Student> students;
    private final String fileName = "students.txt";

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add student
    public void addStudent(Student student) {
        students.add(student);
        saveStudents();
    }

    // View all students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println("------------------------");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Email: " + student.getEmail());
        }

        System.out.println("------------------------");
    }

    // Search student
    public Student searchStudent(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // Update student
    public void updateStudent(int id, Scanner scanner) {

        Student student = searchStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new course: ");
        String course = scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        student.setName(name);
        student.setAge(age);
        student.setCourse(course);
        student.setEmail(email);

        saveStudents();

        System.out.println("Student updated successfully!");
    }

    // Delete student
    public void deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);

        saveStudents();

        System.out.println("Student deleted successfully!");
    }

    // Save students to file
    public void saveStudents() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            for (Student student : students) {

                writer.write(
                        student.getId() + "," +
                        student.getName() + "," +
                        student.getAge() + "," +
                        student.getCourse() + "," +
                        student.getEmail()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error saving students.");
        }
    }

    // Load students from file
    public void loadStudents() {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 5) {

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String course = data[3];
                    String email = data[4];

                    Student student = new Student(
                            id,
                            name,
                            age,
                            course,
                            email
                    );

                    students.add(student);
                }
            }

        } catch (IOException e) {

            // File does not exist yet.
            // It will be created when the first student is added.

        } catch (NumberFormatException e) {

            System.out.println("Invalid data in students.txt.");
        }
    }
}
