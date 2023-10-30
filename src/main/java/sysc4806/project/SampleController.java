package sysc4806.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sysc4806.project.models.Project;

@RestController
public class SampleController {
    @Autowired
    private ProjectRepository repository;
    @GetMapping("/test")
    public String test() {
        repository.save(new Project());
        return "test";
    }
}
