package sysc4806.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @GetMapping(path ="/register")
    public String register() {
        return "register";
    }

//    @PostMapping(path = "/register")
//    public String createUser() {
//        return "test";
//    }
}
