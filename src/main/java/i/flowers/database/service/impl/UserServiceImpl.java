//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.service.impl;

import i.flowers.database.dto.UserRequest;
import i.flowers.database.dto.UserResponse;
import i.flowers.database.model.Role;
import i.flowers.database.model.User;
import i.flowers.database.repository.RoleRepository;
import i.flowers.database.repository.UserRepository;
import i.flowers.exception.UserServiceException;
import i.flowers.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(UserRequest userRequest) {
        User user = userRequest.toUser();
        Role roleUser = this.roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList();
        userRoles.add(roleUser);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        if (this.findByEmail(user.getEmail()) != null) {
            throw new UserServiceException("User already exist");
        } else {
            User registeredUser = (User)this.userRepository.save(user);
            log.info("IN register - user: {} successfully registered", registeredUser);
            return registeredUser;
        }
    }

    public List<UserResponse> getAll() {
        List<UserResponse> result = (new UserResponse()).fromUser(this.userRepository.findAll());
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    public User findByEmail(String username) {
        User result = this.userRepository.findByEmail(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    public User findById(Long id) {
        User result = this.userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        } else {
            log.info("IN findById - user: {} found by id: {}", result);
            return result;
        }
    }

    public UserResponse delete(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserServiceException("no user found by id: " + id);
        } else {
            UserResponse userResponse = UserResponse.fromUser((User)user.get());
            this.userRepository.deleteById(id);
            log.info("IN delete - user with id: {} successfully deleted");
            return userResponse;
        }
    }
}
