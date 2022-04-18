package com.sportshub.controllers;
import com.sportshub.security.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users/")
public class UsersController {
    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getAllUsers{userId}")
    public List<Users> getUsers() {
        return userService.GetUsers();
    }


    @GetMapping(path = "usersRole{userId}")
    public ResponseEntity<Long> getUserRole(@RequestParam long userId) {
        return userService.getUserRole(userId);
    }


    @GetMapping(path = "users{userId}")
    public ResponseEntity<Users> getUser(@RequestParam long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping(path = "users{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable("userId") long userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping(path = {"registerUser", "registerUser{role}"})
    public ResponseEntity<String> regNewUser(@RequestBody Users user,
    @RequestParam(required = false) long roleId) {

        return userService.addNewUser(user,roleId);
    }

    @PutMapping(path = "users{userId}")
    public ResponseEntity updateUser(
            @RequestBody Users user,
            @PathVariable("userId") long userId) {
        return userService.updateUser(userId, user);
    }

}
