package com.sportshub.dto.user.credentials;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserCredentials {

    @ApiModelProperty(example = "test1@gmail.com")
    private String email;

    @ApiModelProperty(example = "pa$$word20!")
    private String password;
}
