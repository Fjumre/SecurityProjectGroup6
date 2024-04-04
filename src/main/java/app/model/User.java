package app.model;

import app.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false, unique = true)
    private int id;
    private String name;
    private String password;
    private String email;
    private int phoneNumber;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }

<<<<<<< Updated upstream
=======

    public User(String name, String password, String email, int phoneNumber) {
        this.name = name;
        this.password = password;
        String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
        this.email= email;
        this.phoneNumber= phoneNumber;
    }

    public User(String password) {
        this.password = password;
        String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }

    public User(String name, Set<Event> events) {
        this.name = name;
        this.events = events;
    }

>>>>>>> Stashed changes
    public boolean verifyUser(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    public Set<String> getRolesAsStrings() {
        if (roles.isEmpty()) {
            return null;
        }
        Set<String> rolesAsStrings = new HashSet<>();
        roles.forEach((role) -> {
            rolesAsStrings.add(role.getName());
        });
        return rolesAsStrings;
    }

    public Set<String> getEventsAsStrings() {
        if (events.isEmpty()) {
            return null;
        }
        Set<String> eventsAsStrings = new HashSet<>();
        events.forEach((event) -> {
            eventsAsStrings.add(event.getTitle());
        });
        return eventsAsStrings;
    }

}