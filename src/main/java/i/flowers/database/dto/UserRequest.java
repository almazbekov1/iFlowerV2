//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.dto;

import i.flowers.database.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;

    public User toUser() {
        return new User(this.fullName, this.phoneNumber, this.email, this.password);
    }

    public static UserRequest fromUser(User user) {
        return new UserRequest(user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getPassword());
    }

    public UserRequest(String fullName, String phoneNumber, String email, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public UserRequest() {
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

    public String getPassword() {
        return this.password;
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

    public void setPassword(final String password) {
        this.password = password;
    }
}
