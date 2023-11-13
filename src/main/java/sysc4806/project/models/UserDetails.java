package sysc4806.project.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetails extends User {
    private final Long userId;
    private final String firebaseUID;

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, authorities);
        this.userId = userId;
        this.firebaseUID = null;
    }

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String firebaseUID) {
        super(username, password, authorities);
        this.userId = null;
        this.firebaseUID = firebaseUID;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirebaseUID() {
        return firebaseUID;
    }
}
