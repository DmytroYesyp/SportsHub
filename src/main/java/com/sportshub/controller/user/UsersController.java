package com.sportshub.controller.user;
import com.sportshub.service.user.impl.UsersServiceImpl;
import com.sportshub.entity.role.Roles;
import com.sportshub.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "api/users/")
public class UsersController {
    private final UsersServiceImpl userService;

    @Autowired
    public UsersController(UsersServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(path = "getAllUsers")
    public List<User> getUsers() {
        return userService.GetUsers();
    }


    @GetMapping(path = "usersRole{userId}")
    public ResponseEntity<Collection<Roles>> getUserRole(@RequestParam long userId) {
        return userService.getUserRole(userId);
    }


    @GetMapping(path = "users{userId}")
    public ResponseEntity<User> getUser(@RequestParam("userId") long userId) {
        return userService.getUserById(userId);
    }


    @DeleteMapping(path = "users{userId}")
    public ResponseEntity<Long> deleteUser(@RequestParam("userId") long userId) {
        return userService.deleteUser(userId);
    }


    @GetMapping("getUserByEmail{email}")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        return userService.findUserByEmail(email);
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
            @RequestParam("userId") long userId) {
            return userService.updateUser(userId, user);
    }

}