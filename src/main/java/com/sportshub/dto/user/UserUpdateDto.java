package com.sportshub.dto.user;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String username;
    private boolean isAdmin;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String info;
}
