//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.controller;

import i.flowers.database.dto.UserRequest;
import i.flowers.database.dto.AuthenticationRequestDto;
import i.flowers.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Random;

@RestController
@RequestMapping({"/api/users"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
public class UserController {
    private final UserService userService;
    private final AuthenticationRestController authenticationRestController;

    public UserController(UserService userService, AuthenticationRestController authenticationRestController) {
        this.userService = userService;
        this.authenticationRestController = authenticationRestController;
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody UserRequest userRequest) {
        AuthenticationRequestDto requestDto = new AuthenticationRequestDto(userRequest.getEmail(), userRequest.getPassword());
        this.userService.register(userRequest);
        return this.authenticationRestController.login(requestDto);
    }
}
