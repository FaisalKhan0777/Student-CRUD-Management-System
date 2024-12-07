package student_Simple_CRUD.entity.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import student_Simple_CRUD.entity.Student;
import student_Simple_CRUD.entity.Stdao.StudentDAO;
import student_Simple_CRUD.entity.Stdao.Service.StudentService;

public class Controller {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        StudentDAO studentDAO = new StudentDAO(em); // 
        StudentService studentService = new StudentService(studentDAO); // display the data of dao class 

        int choice;
        do {
            System.out.println("\n--- > CRUD Operations Menu <---");
            System.out.println("1. Create Student");
            System.out.println("2. Read All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createStudent(studentService);
                case 2 -> readStudents(studentService);
                case 3 -> updateStudent(studentService);
                case 4 -> deleteStudent(studentService);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        em.close();
        emf.close();
        scanner.close();
    }

    private static void createStudent(StudentService studentService) {
        System.out.println("Enter Student Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Student Date of Birth (yyyy-mm-dd):");
        LocalDate dob = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter Student Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Student Phone Number:");
        String phone = scanner.nextLine();

        Student student = new Student();
        student.setName(name);
        student.setDob(dob);
        student.setEmail(email);
        student.setPhone(phone);

        studentService.addStudent(student);
    }

    private static void readStudents(StudentService studentService) {
        List<Student> students = studentService.getAllStudents();
        if (students == null || students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            System.out.println("\n ===> Student Records <=== 2");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void updateStudent(StudentService studentService) {
        System.out.println("Enter Student ID to Update:");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        EntityManager em = emf.createEntityManager();
        Student existingStudent = em.find(Student.class, id);

        if (existingStudent == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Enter New Name (Current: " + existingStudent.getName() + "):");
        existingStudent.setName(scanner.nextLine());

        System.out.println("Enter New Date of Birth (yyyy-mm-dd) (Current: " + existingStudent.getDob() + "):");
        existingStudent.setDob(LocalDate.parse(scanner.nextLine()));

        System.out.println("Enter New Email (Current: " + existingStudent.getEmail() + "):");
        existingStudent.setEmail(scanner.nextLine());

        System.out.println("Enter New Phone Number (Current: " + existingStudent.getPhone() + "):");
        existingStudent.setPhone(scanner.nextLine());

        studentService.updateStudent(existingStudent);
        em.close();
    }

    private static void deleteStudent(StudentService studentService) {
        System.out.println("Enter Student ID to Delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        

        studentService.deleteStudentById(id);
    }
}
