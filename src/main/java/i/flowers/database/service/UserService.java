package i.flowers.service;



import i.flowers.database.model.User;

import java.util.List;


public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByEmail(String name);

    User findById(Long id);

    void delete(Long id);
}
