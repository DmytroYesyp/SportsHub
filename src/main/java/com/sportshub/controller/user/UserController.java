package com.sportshub.controller.user;

import com.sportshub.annotation.Auth;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.user.UserCreateDto;
import com.sportshub.dto.user.UserDto;
import com.sportshub.dto.user.UserUpdateDto;
import com.sportshub.patch.UserPatch;
import com.sportshub.service.user.UserService;
import io.swagger.annotations.ApiImplicitParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid UserCreateDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping
    @Auth(permission = "GET_USERS_LIST")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public List<UserDto> findAll(@RequestParam(defaultValue = "1000") int limit,
                                 @RequestParam(defaultValue = "1") Integer page) {

        return userService.findAll(limit, page);
    }

    @GetMapping("{id}")
    @Auth(permission = "GET_USER_BY_ID")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public UserDto find(@PathVariable Long id) {
        return userService.find(id);
    }

    @GetMapping("count")
    @Auth(permission = "GET_USERS_COUNT")
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public CountDto getCount() {
        return userService.getCount();
    }

    @PutMapping("{id}")
    @Auth(permission = "UPDATE_USER")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public void update(@PathVariable Long id, @RequestBody UserUpdateDto userDto) {
        userService.update(id, userDto);
    }

    @PatchMapping("{id}")
    @Auth(permission = "UPDATE_USER")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public void update(@PathVariable Long id, @RequestBody UserPatch userPatch) {
        userService.patch(id, userPatch);
    }

    @DeleteMapping("{id}")
    @Auth(permission = "DELETE_USER")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "JWT Token", required = true, paramType = "header", example = "Bearer access_token")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
