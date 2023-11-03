package sysc4806.project.models;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Student model
 */
@Entity
public class Student extends ApplicationUser {
    private Program program;

    private Date oralPresentationAvailability;

    @ManyToOne
    private Project project;

    public Student() {
    }

    public Student(String name, String email, String password, Program program) {
        super(name, email, password);
        this.program = program;
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
}
