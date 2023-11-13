package sysc4806.project.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import sysc4806.project.models.ApplicationUser;
import sysc4806.project.models.Professor;
import sysc4806.project.models.Program;
import sysc4806.project.models.Student;

import java.util.Objects;

import static sysc4806.project.util.AuthenticationHelper.PROFESSOR_ROLE;
import static sysc4806.project.util.AuthenticationHelper.STUDENT_ROLE;

public class FirebaseAuthenticationHelper {
    public static String USER_ROLE = "userRole";
    public static String PROGRAM = "program";

    public static ApplicationUser createUserFromFirebaseUID(String UID) {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(UID);
            String role = (String) userRecord.getCustomClaims().get(USER_ROLE);
            String name = userRecord.getDisplayName();
            String email = userRecord.getEmail();
            if (Objects.equals(role, STUDENT_ROLE)) {
                Program program = Program.valueOf((String) userRecord.getCustomClaims().get(PROGRAM));
                return new Student(name, email, "", program);
            } else if (Objects.equals(role, PROFESSOR_ROLE)) {
                return new Professor(name, email, "");
            }
            else {
                throw new Exception("User must have a role of Student or Professor!");
            }
        }
        catch (Exception e) {
            return null;
        }

    }
}
