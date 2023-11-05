package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.Professor;
import sysc4806.project.models.Project;
import sysc4806.project.models.UserDetails;
import sysc4806.project.repositories.ApplicationUserRepository;
import sysc4806.project.repositories.ProjectRepository;
import sysc4806.project.util.AuthenticationHelper;

import static sysc4806.project.util.AuthenticationHelper.PROFESSOR_ROLE;

@Controller
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @GetMapping(path = "/createProject")
    @Secured(PROFESSOR_ROLE)
    public String showProjectForm(Model model) {
        Project project = new Project();
        project.setMaxStudents(1);
        model.addAttribute("project", project);
        return "createProject";
    }

    @PostMapping(path="/createProject")
    @Secured(PROFESSOR_ROLE)
    public String createProject(@ModelAttribute("project") Project project) {
        Professor professor = (Professor) this.getCurrentUser();
        project.setProfessor(professor);
        projectRepository.save(project);
        return "redirect:/home"; //Need to redirect to project list VIEW --> NOAH
    }

    private ApplicationUser getCurrentUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return applicationUserRepository.findApplicationUserByEmail(user.getUsername());
    }
}
