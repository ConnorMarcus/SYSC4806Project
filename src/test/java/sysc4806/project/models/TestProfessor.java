package sysc4806.project.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Date;


public class TestProfessor {
    private Professor professor;

    @BeforeEach
    public void setUp() {
        professor = new Professor();
    }

    @Test
    public void testSetAndGetOralPresentationAvailability() {
        Date oralPresentationDate = new Date();
        professor.setOralPresentationAvailability(oralPresentationDate);
        assertEquals(professor.getOralPresentationAvailability(), oralPresentationDate);
    }

}
