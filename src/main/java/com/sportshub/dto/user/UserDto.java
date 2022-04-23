package com.sportshub.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String state;
    private String info;
}
