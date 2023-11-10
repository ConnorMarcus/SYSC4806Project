package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.UserDetails;
import sysc4806.project.repositories.ApplicationUserRepository;
import sysc4806.project.services.ApplicationUserService;
import sysc4806.project.util.AuthenticationHelper;

import java.util.Optional;

@Controller
public class HomePageController {

    @Autowired
    private ApplicationUserRepository userRepository;
    @Autowired
    private ApplicationUserService userService;

    @GetMapping(path = {"/home", "/"})
    public String getHomePage(Model model) throws Exception {
        ApplicationUser user = userService.getCurrentUser();
        if (user != null) {
            String role = AuthenticationHelper.getUserRole(user);
            model.addAttribute("userType", role);
            return "home";
        }
        return "error";
    }
}
