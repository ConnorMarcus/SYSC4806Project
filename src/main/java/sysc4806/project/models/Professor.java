package sysc4806.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

/**
 * Professor model
 */
@Entity
public class Professor {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String email;

    private String password;

    private Date oralPresentationAvailability;

    public Professor() {}

    /**
     * Get professor's id
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Set professor's id
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get professor's name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set professor's name
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get professor's email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set professor's email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get professor's password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set professor's password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get professor's oral presentation availability
     * @return Date
     */
    public Date getOralPresentationAvailability() {
        return oralPresentationAvailability;
    }

    /**
     * Set professor's oral presentation availability
     * @param oralPresentationAvailability Date
     */
    public void setOralPresentationAvailability(Date oralPresentationAvailability) {
        this.oralPresentationAvailability = oralPresentationAvailability;
    }
}
