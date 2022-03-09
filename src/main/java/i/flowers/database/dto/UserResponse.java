//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.dto;

import i.flowers.database.model.Role;
import i.flowers.database.model.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserResponse {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String role;

    public User toUser() {
        return new User(this.id, this.fullName, this.phoneNumber, this.email);
    }

    public static UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), ((Role)user.getRoles().get(0)).getName());
    }

    public List<UserResponse> fromUser(List<User> userList) {
        List<UserResponse> userRequests = new ArrayList();
        Iterator var3 = userList.iterator();

        while(var3.hasNext()) {
            User u = (User)var3.next();
            userRequests.add(fromUser(u));
        }

        return userRequests;
    }

    public UserResponse(Long id, String fullName, String phoneNumber, String email, String role) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

    public UserResponse() {
    }

    public Long getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setRole(final String role) {
        this.role = role;
    }
}
