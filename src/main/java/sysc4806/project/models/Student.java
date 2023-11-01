package sysc4806.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String email;

    private String password;

    private Program program;

    private Date oralPresentationAvailability;

    @ManyToOne
    private Project project;

    public Student() {
    }

    public Student(String name,
                   String email,
                   String password,
                   Program program,
                   Date oralPresentationAvailability) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.program = program;
        this.oralPresentationAvailability = oralPresentationAvailability;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Date getOralPresentationAvailability() {
        return oralPresentationAvailability;
    }

    public void setOralPresentationAvailability(Date oralPresentationAvailability) {
        this.oralPresentationAvailability = oralPresentationAvailability;
    }
}
