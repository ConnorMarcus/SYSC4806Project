package sysc4806.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Professor {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String email;

    private String password;

    private Date oralPresentationAvailability;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getOralPresentationAvailability() {
        return oralPresentationAvailability;
    }

    public void setOralPresentationAvailability(Date oralPresentationAvailability) {
        this.oralPresentationAvailability = oralPresentationAvailability;
    }
}
