package sysc4806.project.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sysc4806.project.WithMockStudent;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestRegisterController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockStudent
    public void testRegisterLoggedIn() throws Exception {
        this.mockMvc.perform(get("/register/Student")).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("home"));
        this.mockMvc.perform(get("/register/Professor")).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("home"));
    }

    @Test
    public void testRegisterLoggedOut() throws Exception {
        this.mockMvc.perform(get("/register/Professor")).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("professorRegister"))
                .andExpect(model().attributeExists("professor"));
        this.mockMvc.perform(get("/register/Student")).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("studentRegister"))
                .andExpect(model().attributeExists("student"));
    }
}
