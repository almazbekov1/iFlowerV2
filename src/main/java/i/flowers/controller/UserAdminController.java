//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.controller;

import i.flowers.database.dto.UserResponse;
import i.flowers.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/admin/users"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
public class UserAdminController {
    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> users = this.userService.getAll();
        users.remove(0);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable Long id) {
        return new ResponseEntity(this.userService.delete(id), HttpStatus.OK);
    }
}
