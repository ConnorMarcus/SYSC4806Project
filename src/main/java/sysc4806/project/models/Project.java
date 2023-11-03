package sysc4806.project.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project model
 */
@Entity
public class Project {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String description;

    private int maxStudents;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
    private List<Student> students;

    @ManyToOne
    private Professor professor;

    public Project() {
        this.students = new ArrayList<>();
    }

    public Project(String title, String description, int maxStudents, Professor professor) {
        this.title= title;
        this.description = description;
        this.maxStudents = maxStudents;
        this.students = new ArrayList<>();
        this.professor = professor;
    }

    /**
     * Get project title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set project title
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get project id
     */
    public long getId() {
        return id;
    }

    /**
     * Set project id
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get project description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set project description
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get project professor(s)
     * @return Professor
     */
    public Professor getProfessors() {
        return professor;
    }

    /**
     * Get maximum number of students assigned to a project
     * @return int maximum amount of students on project
     */
    public int getMaxStudents() {
        return maxStudents;
    }

    /**
     * Set maximum number of students for a project
     * @param maxStudents int maximum number of students on project
     */
    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    /**
     * Get students assigned to the project
     * @return List of students on the project
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Add a student to the project
     * @param student Student to be added to the project
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Remove student from the project
     * @param student Student to be removed from the project
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }
}
