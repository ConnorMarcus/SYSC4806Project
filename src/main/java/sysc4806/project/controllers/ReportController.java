package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.Project;
import sysc4806.project.models.ReportFile;
import sysc4806.project.models.Student;
import sysc4806.project.services.ApplicationUserService;
import sysc4806.project.services.ReportService;

import static sysc4806.project.util.AuthenticationHelper.PROFESSOR_ROLE;
import static sysc4806.project.util.AuthenticationHelper.STUDENT_ROLE;

@Controller
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private ApplicationUserService userService;

    @GetMapping(path = "/studentReportUpload")
    @Secured(STUDENT_ROLE)
    public String uploadReportPage(Model model) {
        Student user = (Student) userService.getCurrentUser();
        if (user.getProject() != null) {
            ReportFile report = user.getProject().getReport();
            model.addAttribute("inProject", true);
            model.addAttribute("reportName", report == null ? "" : report.getFileName());
        }
        else {
            model.addAttribute("inProject", false);
        }

        return "studentReportUpload";
    }

    @PostMapping(path = "/studentReportUpload")
    @Secured(STUDENT_ROLE)
    public String uploadReport(@RequestParam("file") MultipartFile file, Model model) {
        Student user = (Student) userService.getCurrentUser();
        if (user.getProject() != null) {
            try {
                reportService.saveReport(user.getProject(), file);
            }
            catch (Exception e) {
                model.addAttribute("uploadError", "There was an error uploading the file!");
            }
            model.addAttribute("inProject", true);
            model.addAttribute("reportName", file.getOriginalFilename());
            return "studentReportUpload";
        }

        return "redirect:/home"; // Redirect to home page if project is null
    }

}
