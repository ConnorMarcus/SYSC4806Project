package sysc4806.project.models;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Student model
 */
@Entity
public class Student extends ApplicationUser {

    private String reminder;

    private Program program;

    private Date oralPresentationAvailability;

    @ManyToOne
    private Project project;

    public Student() {
    }

    public Student(String name, String email, String password, Program program) {
        super(name, email, password);
        this.program = program;
        this.reminder = "";
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

    /**
     * Get student's reminder
     * @return String
     */
    public String getReminder() {
        return reminder;
    }

    /**
     * Set student's reminder
     * @param reminder String
     */
    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
