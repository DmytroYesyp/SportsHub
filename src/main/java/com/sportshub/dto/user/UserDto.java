package com.sportshub.dto.user;

import lombok.Data;

import java.util.Set;

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
    private Set<Long> roleIds;
    private Set<Long> teamIds;
    private Set<Long> leagueIds;
    private Set<Long> surveyIds;
}
