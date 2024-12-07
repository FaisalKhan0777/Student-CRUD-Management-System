package student_Simple_CRUD.entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
public class Student implements Serializable {

    /** @author Faisal khan
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private String phone;

    public Student() {
    }

    public Student(int id, String name, LocalDate dob, String email, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
/* @faisal khan*/
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
