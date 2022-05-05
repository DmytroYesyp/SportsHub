package com.sportshub.controller.user;
import com.sportshub.service.user.UsersService;
import com.sportshub.entity.role.Roles;
import com.sportshub.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/users/")
public class UsersController {
    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getAllUsers")
    public List<User> getUsers() {
        return userService.GetUsers();
    }



    @GetMapping(path = "getUserByEmail{email}")
    public User getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }


    @GetMapping(path = "usersRole{userId}")
    public ResponseEntity<Set<Roles>> getUserRole(@RequestParam long userId) {
        return userService.getUserRole(userId);
    }


    @GetMapping(path = "users{userId}")
    public ResponseEntity<User> getUser(@RequestParam long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping(path = "users{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable("userId") long userId) {
        return userService.deleteUser(userId);
    }


    @PostMapping(path = "setRole{userId}{roleId}")
    public ResponseEntity<String> setRole(@RequestParam long userId,@RequestParam long roleId){

        return userService.setRole(userId,roleId);
    }

    @PostMapping(path = {"registerUser"})
    public ResponseEntity<String> regNewUser(@RequestBody User user) {

        return userService.addNewUser(user);
    }

    @PutMapping(path = "users{userId}")
    public ResponseEntity updateUser(
            @RequestBody User user,
            @PathVariable("userId") long userId) {
        return userService.updateUser(userId, user);
    }

}