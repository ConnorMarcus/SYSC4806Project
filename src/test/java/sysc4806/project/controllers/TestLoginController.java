package sysc4806.project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import sysc4806.project.WithMockStudent;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.Professor;
import sysc4806.project.repositories.ApplicationUserRepository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static sysc4806.project.util.AuthenticationHelper.STUDENT_ROLE;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TestLoginController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private LoginController controller;

    @MockBean
    private ApplicationUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockStudent
    public void testUserLoggedIn() throws Exception {
        this.mockMvc.perform(get("/login").flashAttr("userType", STUDENT_ROLE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("home"));
    }

    @Test
    public void testUserNotLoggedIn() throws Exception {
        this.mockMvc.perform(get("/login")).andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login"));
    }

    @Test
    public void testLogUserIn() throws Exception {
        String email = "EMAIL";
        String password = "PASSWORD";
        ApplicationUser user = new Professor("name", email, passwordEncoder.encode(password));
        when(userRepository.findApplicationUserByEmail(email)).thenReturn(user);

        // Test Valid Credentials
        this.mockMvc.perform(post("/loginHandler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .param("email", email).param("password", password))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/home"));

        // Test Invalid Credentials
        user.setPassword(passwordEncoder.encode("WRONG_PASSWORD"));
        this.mockMvc.perform(post("/loginHandler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("email", email).param("password", password))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/login?error"));
    }
}
