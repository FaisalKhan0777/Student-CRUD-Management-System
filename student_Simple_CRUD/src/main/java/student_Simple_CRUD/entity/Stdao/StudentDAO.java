package student_Simple_CRUD.entity.Stdao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import student_Simple_CRUD.entity.Student;

public class StudentDAO {
    
	private EntityManager em;

    public StudentDAO(EntityManager em) {
        this.em = em;
    }

    // ---------------[Create-------------------------------------------]
    
    public void createStudent(Student student) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(student);
            et.commit();
            System.out.println("Student record created successfully!");
        } catch (Exception e) {
            et.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }
    

    //--------------------- [ Read All ] -----------------------------------------
    public List<Student> getAllStudents() {
        try {
            Query query = em.createQuery("SELECT s FROM Student s", Student.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    
    //---------------------- [ Update ] -----------------------------------------------
    public void updateStudent(Student student) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(student);
            et.commit();
            System.out.println("Student record updated successfully!");
        } catch (Exception e) {
            et.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }

    //---------------------------[ DELETE ]-------------------------------------------------
    public void deleteStudent(int id) {
        EntityTransaction et = em.getTransaction();
        try {
            Student student = em.find(Student.class, id);
            if (student != null) {
                et.begin();
                em.remove(student);
                et.commit();
                System.out.println("Student record deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }
        } catch (Exception e) {
            et.rollback();
            System.err.println("Error: " + e.getMessage());
        }
    }
}
