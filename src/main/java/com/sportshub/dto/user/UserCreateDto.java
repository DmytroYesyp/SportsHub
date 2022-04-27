package com.sportshub.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class UserCreateDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String middleName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^(\\+38)?\\d{10}$")
    private String phone;

    private String state;

    private String info;

    private Set<Long> teamIds;

    private Set<Long> leagueIds;

    private Set<Long> surveyIds;
}
