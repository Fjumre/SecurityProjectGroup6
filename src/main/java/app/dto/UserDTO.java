package app.dto;



import app.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Setter
public class UserDTO {

    private String name;
    private String password;
    private String email;
    private int phoneNumber;

    private Set<String> roles;
    private Set<String> events;
    private String newPassword;

    public UserDTO(String name, String password, Set<String> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public UserDTO(String name, String password, String email, int phoneNumber) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserDTO(User user){
        this.name = user.getName();
        this.email= user.getEmail();
        this.phoneNumber= user.getPhoneNumber();
        if (user.getRoles() != null) {
            this.roles = user.getRolesAsStrings();
        }
        if (user.getEvents() != null) {
            this.events = user.getEventsAsStrings();
        }
    }

    public UserDTO(String name, Set<String> roleSet){
        this.name = name;
        this.roles = roleSet;
    }

    public static List<UserDTO> toUserDTOList(List<User> users) {
        List<UserDTO> userDTOList =  new ArrayList<>();
        for (User user : users) {
            userDTOList.add(new UserDTO(user.getName(), user.getRolesAsStrings()));
        }
        return userDTOList;

    }

    public String getName() {
        return name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }


    public Set<String> getEvents() {
        return events;
    }


}
