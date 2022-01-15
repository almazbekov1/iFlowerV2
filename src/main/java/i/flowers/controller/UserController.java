package i.flowers.controller;


import i.flowers.database.dto.UserRequest;
import i.flowers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import i.flowers.database.model.User;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRequest user){
        User result =  userService.register(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
