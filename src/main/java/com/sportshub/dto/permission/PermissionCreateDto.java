package com.sportshub.dto.permission;

import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class PermissionCreateDto {
    @NotBlank
    private String name;
}
