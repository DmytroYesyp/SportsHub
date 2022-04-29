//package com.sportshub.controller.user;
//
//import com.sportshub.dto.user.UserCreateDto;
//import com.sportshub.dto.user.UserDto;
//import com.sportshub.dto.user.UserUpdateDto;
//import com.sportshub.patch.UserPatch;
//import com.sportshub.service.user.UserService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("users")
//public class UserController {
//
//    private final UserService userService;
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserDto create(@RequestBody @Valid UserCreateDto userDto) {
//        return userService.create(userDto);
//    }
//
//    @GetMapping
//    public List<UserDto> findAll() {
//        return userService.findAll();
//    }
//
//    @GetMapping("{id}")
//    public UserDto find(@PathVariable Long id) {
//        return userService.find(id);
//    }
//
//    @PutMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
//        userService.update(id, userDto);
//    }
//
//    @PatchMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@PathVariable Long id, @RequestBody UserPatch userPatch ) {
//        userService.patch(id, userPatch);
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Long id) {
//        userService.delete(id);
//    }
//}
