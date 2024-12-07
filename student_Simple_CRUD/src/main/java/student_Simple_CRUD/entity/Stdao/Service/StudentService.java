package student_Simple_CRUD.entity.Stdao.Service;


import java.util.List;

import student_Simple_CRUD.entity.Student;
import student_Simple_CRUD.entity.Stdao.StudentDAO;

public class StudentService {
    private StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) 
    {
        this.studentDAO = studentDAO;
  
    }

    // Create student Table -------------------
    public void addStudent(Student student) {
        if (student != null && isValidStudent(student)) {
            studentDAO.createStudent(student);
        } else {
            System.out.println("Invalid student data. Cannot create student.");
        }
    }

    //------------- Display ALL students DATA 
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // ------------ UpdatION DATA ----------
    public void updateStudent(Student student) {
        if (student != null && student.getId() != 0 && isValidStudent(student)) {
            studentDAO.updateStudent(student);
        } else {
            System.out.println("Invalid student data. Cannot update student.");
        }
    }

//-------Deletation Data ----------------------------------
    public void deleteStudentById(int id) {
        if (id > 0) {
            studentDAO.deleteStudent(id);
        } else {
            System.out.println("Invalid student ID. Cannot delete student.");
        }
    }

    //   Check Validate of data ----------------------------------
    private boolean isValidStudent(Student student) {
        return student.getName() != null && !student.getName().isEmpty() &&
               student.getDob() != null &&
               student.getEmail() != null && !student.getEmail().isEmpty() &&
               student.getPhone() != null && !student.getPhone().isEmpty();
    }
}
