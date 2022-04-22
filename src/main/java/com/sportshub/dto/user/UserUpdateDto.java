package com.sportshub.dto.user;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String info;
}
