package i.flowers.database.dto;

import i.flowers.database.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class UserResponse {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;

    public User toUser(){
        return new User(id,fullName,phoneNumber,email);
    }
    public static UserResponse fromUser(User user){
        return new UserResponse(user.getId(),user.getFullName(),user.getPhoneNumber(),user.getEmail());

    }
    public List<UserResponse> fromUsers(List<User> userList){
        List<UserResponse> userRequests = new ArrayList<>();
        for (User u: userList) {
            userRequests.add(fromUser(u));
        }
        return userRequests;
    }

    public UserResponse(Long id,String fullName, String phoneNumber, String email) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserResponse() {
    }

}
