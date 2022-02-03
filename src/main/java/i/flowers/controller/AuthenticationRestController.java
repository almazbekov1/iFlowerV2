//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.controller;

import i.flowers.config.jwt.JwtTokenProvider;
import i.flowers.database.model.Role;
import i.flowers.database.model.User;
import i.flowers.database.dto.AuthenticationRequestDto;
import i.flowers.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/auth"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping({"/login"})
    @CrossOrigin
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = this.userService.findByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            } else {
                String token = this.jwtTokenProvider.createToken(username, user.getRoles());
                Map<Object, Object> response = new HashMap();
                response.put("username", username);
                response.put("role", ((Role)user.getRoles().get(0)).getName());
                response.put("token", token);
                return ResponseEntity.ok(response);
            }
        } catch (AuthenticationException var6) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
