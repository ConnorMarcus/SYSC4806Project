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
        Project project = new Project();
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

    @Test
    public void testGetAndSetReminder() {
        String reminder = "Please join the group";
        student.setReminder(reminder);
        assertEquals(student.getReminder(), reminder);
    }

}
