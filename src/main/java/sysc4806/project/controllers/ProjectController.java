package sysc4806.project.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static sysc4806.project.util.AuthenticationHelper.PROFESSOR_ROLE;

@Controller
public class ProjectController {

    @GetMapping(path = "/createProject")
    @Secured(PROFESSOR_ROLE)
    public String createProjectPage() {
        return "createProject";
    }
}
