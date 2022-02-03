//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.service;

import i.flowers.database.dto.UserRequest;
import i.flowers.database.dto.UserResponse;
import i.flowers.database.model.User;
import java.util.List;

public interface UserService {
    User register(UserRequest user);

    List<UserResponse> getAll();

    User findByEmail(String name);

    User findById(Long id);

    UserResponse delete(Long id);
}
