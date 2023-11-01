package sysc4806.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

/**
 * Student model
 */
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

    /**
     * Get students project
     * @return Project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Set students project
     * @param project Project
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Get student's id
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Set student's id
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get student's name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set student's name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get student's email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set student's email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get student's password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set student's password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get student's program
     * @return Program
     */
    public Program getProgram() {
        return program;
    }

    /**
     * Set student's program
     * @param program Program
     */
    public void setProgram(Program program) {
        this.program = program;
    }

    /**
     * Get student's oral presentation availability
     * @return Date
     */
    public Date getOralPresentationAvailability() {
        return oralPresentationAvailability;
    }

    /**
     * Set student's oral presentation availability
     * @param oralPresentationAvailability Date
     */
    public void setOralPresentationAvailability(Date oralPresentationAvailability) {
        this.oralPresentationAvailability = oralPresentationAvailability;
    }
}
