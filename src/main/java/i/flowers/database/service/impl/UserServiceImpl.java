package i.flowers.database.service.impl;

import i.flowers.database.dto.UserRequest;
import i.flowers.database.dto.UserResponse;
import i.flowers.database.model.Role;
import i.flowers.database.model.User;
import i.flowers.database.repository.RoleRepository;
import i.flowers.database.repository.UserRepository;
import i.flowers.service.UserService;
import i.flowers.exception.UserServiceException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
    @Override
    public User register(UserRequest userRequest) {

        User user = userRequest.toUser();

        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        if (findByEmail(user.getEmail())!=null){
            throw new UserServiceException("User already exist");
        }


        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

//        return UserResponse.fromUser(registeredUser);
        return registeredUser;
    }


    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> result = new UserResponse().fromUser(userRepository.findAll());
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String username) {
        User result = userRepository.findByEmail(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public UserResponse delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new UserServiceException("no user found by id: "+ id);
        }
        UserResponse userResponse = UserResponse.fromUser(user.get());
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
        return userResponse;

    }
}
