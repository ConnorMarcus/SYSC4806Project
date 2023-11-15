package sysc4806.project.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.Professor;
import sysc4806.project.models.Student;
import sysc4806.project.models.UserDetails;
import sysc4806.project.repositories.ApplicationUserRepository;
import sysc4806.project.util.AuthenticationHelper;
import java.util.*;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
import static sysc4806.project.firebase.FirebaseAuthenticationHelper.*;
import static sysc4806.project.util.AuthenticationHelper.isUserLoggedIn;

@Controller
public class FirebaseAuthController {
    @Autowired
    ApplicationUserRepository userRepository;

    @GetMapping(path = "/signup/Professor")
    public String signUpProfessorPage(Model model) {
        if (isUserLoggedIn()) {
            return "home";
        }

        Professor professor = new Professor();
        model.addAttribute("professor", professor);
        return "professorRegisterFirebase";
    }

    @GetMapping(path = "/signup/Student")
    public String signUpStudentPage(Model model) {
        if (isUserLoggedIn()) {
            return "home";
        }

        Student student = new Student();
        model.addAttribute("student", student);
        return "studentRegisterFirebase";
    }

    @PostMapping(path = "/signup/Professor")
    public String signUp(@ModelAttribute Professor professor) {
        try {
            UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                    .setEmail(professor.getEmail())
                    .setPassword(professor.getPassword())
                    .setDisplayName(professor.getName());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);
            Map<String, Object> customFields = new HashMap<>();
            customFields.put(USER_ROLE, AuthenticationHelper.PROFESSOR_ROLE);
            FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), customFields);
            saveApplicationUser(userRecord.getUid());
            return "redirect:/firebaseLogin";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/signup/Professor?error";
        }
    }

    @PostMapping(path = "/signup/Student")
    public String signUp(@ModelAttribute Student student) {
        try {
            UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                    .setEmail(student.getEmail())
                    .setPassword(student.getPassword())
                    .setDisplayName(student.getName());


            UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);
            Map<String, Object> customFields = new HashMap<>();
            customFields.put(USER_ROLE, AuthenticationHelper.STUDENT_ROLE);
            customFields.put(PROGRAM, student.getProgram().name());
            FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), customFields);
            saveApplicationUser(userRecord.getUid());
            return "redirect:/firebaseLogin";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/signup/Student?error";
        }
    }

    @PostMapping(path = "/firebaseLogin")
    public String logUserIn(HttpServletRequest req,
                            @RequestParam(name = "token") String token,
                            @RequestParam(name = "role") String role) throws Exception {
        
        FirebaseToken decodedToken;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/login?error";
        }

        List<GrantedAuthority> authorityList = List.of(new SimpleGrantedAuthority(role));
        SecurityContext sc = SecurityContextHolder.getContext();

        //TODO: Remove after migrating fully to Firebase
        if (!userRepository.existsApplicationUserByEmail(decodedToken.getEmail())) {
            saveApplicationUser(decodedToken.getUid());
        }
        ApplicationUser applicationUser = userRepository.findApplicationUserByEmail(decodedToken.getEmail());

        UserDetails userDetails = new UserDetails(decodedToken.getEmail(), token, authorityList, applicationUser.getId());
        sc.setAuthentication(
                new FirebaseAuthenticationToken(userDetails, token, authorityList)
        );
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return "redirect:/home";
    }

    //Save application user to user repository until all logic is converted to using firebase
    private void saveApplicationUser(String uid) {
        ApplicationUser user = createUserFromFirebaseUID(uid);
        if (user != null) {
            userRepository.save(user);
        }
    }
}
