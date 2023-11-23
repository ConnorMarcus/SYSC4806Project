package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.ReportFile;
import sysc4806.project.models.Student;
import sysc4806.project.repositories.ApplicationUserRepository;
import sysc4806.project.services.ApplicationUserService;

import java.io.IOException;
import java.nio.file.Files;

import static sysc4806.project.util.AuthenticationHelper.STUDENT_ROLE;

@RestController
public class DownloadController {
    @Autowired
    private ApplicationUserService userService;

    @RequestMapping(path = "/downloadReport", method = RequestMethod.GET)
    @Secured(STUDENT_ROLE)
    public ResponseEntity<Resource> download() throws IOException {
        Student user = (Student) userService.getCurrentUser();
        if (user.getProject() != null && user.getProject().getReport() != null) {
            ReportFile reportFile = user.getProject().getReport();
            ByteArrayResource resource = new ByteArrayResource(reportFile.getFileData());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportFile.getFileName());
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(reportFile.getFileData().length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        }

        return ResponseEntity.badRequest().build();
    }
}
