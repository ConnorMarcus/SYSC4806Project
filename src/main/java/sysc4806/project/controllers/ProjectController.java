package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sysc4806.project.models.*;
import sysc4806.project.repositories.ApplicationUserRepository;
import sysc4806.project.repositories.ProjectRepository;
import sysc4806.project.services.ApplicationUserService;
import java.util.List;


import static sysc4806.project.util.AuthenticationHelper.*;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private ApplicationUserService applicationUserService;

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
        Professor professor = (Professor) applicationUserService.getCurrentUser();
        project.setProfessor(professor);
        projectRepository.save(project);
        return "redirect:/viewProjects";
    }

    @GetMapping(path = "/viewProjects")
    public String viewProjectsPage(Model model) throws Exception {
        List<Project> projects = (List<Project>) projectRepository.findAll();
        model.addAttribute("projects", projects);

        boolean isProfessor = applicationUserService.isCurrentUserProfessor();
        model.addAttribute("isProfessor", isProfessor);

        if (isProfessor) {
            Professor professor = (Professor) applicationUserService.getCurrentUser();
            model.addAttribute("professorUser", professor);
        } else {
            Student student = (Student) applicationUserService.getCurrentUser();
            model.addAttribute("studentUser", student);
        }
        return "viewProjects";
    }

    @DeleteMapping( "/deleteProject/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project != null) {
            disassociateProject(project);
            projectRepository.delete(project);
            return "viewProjects";
        } else {
            return "error";
        }
    }

    @PatchMapping("/project/{projectId}/addStudent/{userId}")
    public String addStudentToProject(@PathVariable Long projectId, @PathVariable Long userId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Student student = (Student) applicationUserRepository.findById(userId).orElse(null);
        if (project != null && student != null) {
            project.addStudent(student);
            student.setProject(project);
            projectRepository.save(project);
            return "viewProjects";
        }
        return "error";
    }

    @PatchMapping("/project/{projectId}/removeStudent/{userId}")
    public String removeStudentFromProject(@PathVariable Long projectId, @PathVariable Long userId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Student student = (Student) applicationUserRepository.findById(userId).orElse(null);
        if (project != null && student != null) {
            project.removeStudent(student);
            student.setProject(null);
            projectRepository.save(project);
            return "viewProjects";
        }
        return "error";
    }

    /**
     * Disassociates a project from students and professor
     * @param project The project to disassociate
     */
    private void disassociateProject(Project project) {
        for (Student s: project.getStudents()) {
            s.setProject(null);
            applicationUserRepository.save(s);
        }
        Professor professor = project.getProfessor();
        professor.removeProject(project);
        applicationUserRepository.save(professor);
    }
}
