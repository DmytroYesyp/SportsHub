package com.sportshub.dto.role;

import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class RoleCreateDto {
    @NotBlank
    private String name;
    private Set<Long> permissionIds;
}
