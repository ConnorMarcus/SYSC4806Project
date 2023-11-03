package sysc4806.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping(path = "/home")
    public String getHomePage() {
        return "home";
    }
}
