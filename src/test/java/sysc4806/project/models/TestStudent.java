package sysc4806.project.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class TestStudent {

    private Student student;

    @BeforeEach
    public void setUp() {
        student  = new Student();
    }

    @Test
    public void testGetAndSetProject() {
        Professor professor = new Professor();
        Project project = new Project("Test Project", "Project for Testing", 10, professor);
        student.setProject(project);
        assertEquals(student.getProject(), project);
    }

    @Test
    public void testGetAndSetProgram() {
        student.setProgram(Program.SOFTWARE_ENGINEERING);
        assertEquals(student.getProgram(), Program.SOFTWARE_ENGINEERING);
    }

    @Test
    public void testGetAndSetOralPresentationAvailability() {
        Date oralPresentationAvailability = new Date();
        student.setOralPresentationAvailability(oralPresentationAvailability);
        assertEquals(student.getOralPresentationAvailability(), oralPresentationAvailability);
    }

}
