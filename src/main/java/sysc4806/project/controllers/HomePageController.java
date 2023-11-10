package sysc4806.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.UserDetails;
import sysc4806.project.repositories.ApplicationUserRepository;
import sysc4806.project.util.AuthenticationHelper;

import java.util.Optional;

@Controller
public class HomePageController {

    @Autowired
    private ApplicationUserRepository userRepository;
    @GetMapping(path = {"/home", "/"})
    public String getHomePage(Model model) throws Exception {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<ApplicationUser> user = userRepository.findById(userDetails.getUserId());
        if (user.isPresent()) {
            String role = AuthenticationHelper.getUserRole(user.get());
            model.addAttribute("userType", role);
            return "home";
        }
        return "error";
    }
}
