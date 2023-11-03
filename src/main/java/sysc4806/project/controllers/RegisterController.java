package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sysc4806.project.models.Professor;
import sysc4806.project.models.Student;
import sysc4806.project.repositories.ApplicationUserRepository;

import static sysc4806.project.util.AuthenticationHelper.isUserLoggedIn;

@Controller
public class RegisterController {
    @Autowired
    private ApplicationUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path ="/register/Professor")
    public String registerProf(Model model) {
        if (isUserLoggedIn()) {
            return "home";
        }

        Professor professor = new Professor();
        model.addAttribute("professor", professor);
        return "professorRegister";
    }

    @GetMapping(path ="/register/Student")
    public String registerStudent(Model model) {
        if (isUserLoggedIn()) {
            return "home";
        }

        Student student = new Student();
        model.addAttribute("student", student);
        return "studentRegister";
    }

    @PostMapping(path = "/register/Professor")
    public String createProfessor(@ModelAttribute Professor professor) {
        if (userRepository.findApplicationUserByEmail(professor.getEmail()) != null) {
            return "redirect:/register/Professor?error";
        }
        professor.setPassword(passwordEncoder.encode(professor.getPassword()));
        userRepository.save(professor);
        return "redirect:/login";
    }

    @PostMapping(path = "/register/Student")
    public String createStudent(@ModelAttribute Student student) {
        if (userRepository.findApplicationUserByEmail(student.getEmail()) != null) {
            return "redirect:/register/Student?error";
        }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        userRepository.save(student);
        return "redirect:/login";
    }
}
