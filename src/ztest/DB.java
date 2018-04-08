package ztest;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "TESTOne")
public class DB implements Serializable{
    private int id;
    private String Fname;
    private String Lname;
   // private LocalDate date;
    private String date; 
    private String gender;
    private String Role;

@Id
@GeneratedValue(strategy = GenerationType.TABLE)
@Column(name = "P_Number")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
@Column(name = "FirstName",length = 250,nullable = true)

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    
@Column(name = "LastName",length = 250,nullable = true)

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }
    
@Column(name = "DateOfBirth",nullable = true)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
@Column(name = "Gender",length = 250,nullable = true)

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
@Column(name = "E_Role",length = 250,nullable = true)

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
@Column(name = "Email",length = 250,nullable = true)

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
@Column(name = "Passsword",length = 250,nullable = true)

    public String getPossword() {
        return possword;
    }

    public void setPossword(String possword) {
        this.possword = possword;
    }

    
    private String Email;
    private String possword;

   
    

}
