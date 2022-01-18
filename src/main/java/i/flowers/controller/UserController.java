package i.flowers.controller;


import i.flowers.database.dto.UserRequest;
import i.flowers.dto.AuthenticationRequestDto;
import i.flowers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final AuthenticationRestController authenticationRestController;

    public UserController(UserService userService, AuthenticationRestController authenticationRestController) {
        this.userService = userService;
        this.authenticationRestController = authenticationRestController;
    }


    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserRequest userRequest) {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto(
                userRequest.getEmail(),userRequest.getPassword());
        userService.register(userRequest);
        return authenticationRestController.login(requestDto);

    }


}
