package com.sportshub.dto.role;

import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {
    private Long id;
    private String name;
    private Set<Long> permissionIds;
}
