package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sysc4806.project.models.*;
import sysc4806.project.repositories.ApplicationUserRepository;
import sysc4806.project.repositories.ProjectRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static sysc4806.project.util.AuthenticationHelper.*;

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
        return "redirect:/viewProjects";
    }

    @GetMapping(path = "/viewProjects")
    public String viewProjectsPage(Model model) throws Exception {
        List<Project> projects = (List<Project>) projectRepository.findAll();
        model.addAttribute("projects", projects);
        model.addAttribute("isProfessor", isCurrentUserProfessor());
        model.addAttribute("userProjects", getUserProjects());
        return "viewProjects";
    }

    @DeleteMapping( "/deleteProject/{projectID}")
    public String deleteProject(@PathVariable Long projectID) {
        Optional<Project> projectWithID = projectRepository.findById(projectID);
        if (projectWithID.isPresent()) {
            Project project = projectWithID.get();
            disassociateProject(project);
            projectRepository.delete(project);
            return "viewProjects";
        } else {
            return "error";
        }
    }

    private void disassociateProject(Project project) {
        for (Student s: project.getStudents()) {
            s.setProject(null);
        }
        project.getProfessor().removeProject(project);
    }

    private ApplicationUser getCurrentUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return applicationUserRepository.findApplicationUserByEmail(user.getUsername());
    }

    private List<Long> getUserProjects() throws Exception {
        List<Long> listOfProjectIDs = new ArrayList<>();
        ApplicationUser currentUser = getCurrentUser();

        if (!isCurrentUserProfessor()) {
            listOfProjectIDs.add(((Student)currentUser).getProject().getId());
        }
        else {
            for (Project project: ((Professor)currentUser).getProjects()) {
                listOfProjectIDs.add(project.getId());
            }
        }
        return listOfProjectIDs;
    }

    private boolean isCurrentUserProfessor() throws Exception {
        ApplicationUser currentUser = getCurrentUser();
        return Objects.equals(getUserRole(currentUser), PROFESSOR_ROLE);
    }

}
