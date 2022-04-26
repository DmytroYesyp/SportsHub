//package com.sportshub.controllers;
//import com.sportshub.security.entities.Roles;
//import com.sportshub.security.entities.Users;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Set;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping(path = "api/users/")
//public class UsersController {
//    private final UsersService userService;
//
//    @Autowired
//    public UsersController(UsersService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping(path = "getAllUsers{userId}")
//    public List<Users> getUsers() {
//        return userService.GetUsers();
//    }
//
//
//    @GetMapping(path = "usersRole{userId}")
//    public ResponseEntity<Set<Roles>> getUserRole(@RequestParam long userId) {
//        return userService.getUserRole(userId);
//    }
//
//
//    @GetMapping(path = "users{userId}")
//    public ResponseEntity<Users> getUser(@RequestParam long userId) {
//        return userService.getUserById(userId);
//    }
//
//    @DeleteMapping(path = "users{userId}")
//    public ResponseEntity<Long> deleteUser(@PathVariable("userId") long userId) {
//        return userService.deleteUser(userId);
//    }
//
//
//    @PostMapping(path = "setRole{userId}{roleId}")
//    public ResponseEntity<String> setRole(@RequestParam long userId,@RequestParam long roleId){
//
//        return userService.setRole(userId,roleId);
//    }
//
//    @PostMapping(path = {"registerUser"})
//    public ResponseEntity<String> regNewUser(@RequestBody Users user) {
//
//        return userService.addNewUser(user);
//    }
//
//    @PutMapping(path = "users{userId}")
//    public ResponseEntity updateUser(
//            @RequestBody Users user,
//            @PathVariable("userId") long userId) {
//        return userService.updateUser(userId, user);
//    }
//
//}
