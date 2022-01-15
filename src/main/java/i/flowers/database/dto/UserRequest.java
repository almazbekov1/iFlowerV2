package i.flowers.database.dto;

import i.flowers.database.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;

    public User toUser(){
        return new User(fullName,phoneNumber,email,password);
    }
    public static UserRequest fromUser(User user){
        return new UserRequest(user.getFullName(),user.getPhoneNumber(),user.getEmail(),user.getPassword());

    }

    public UserRequest(String fullName, String phoneNumber, String email, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public UserRequest() {
    }
}
