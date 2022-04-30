package com.sportshub.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateDto {
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String info;
    private Set<Long> roleIds;
    private Set<Long> teamIds;
    private Set<Long> leagueIds;
    private Set<Long> surveyIds;
}
