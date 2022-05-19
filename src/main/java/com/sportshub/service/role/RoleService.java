package com.sportshub.service.role;

import com.sportshub.entity.role.Roles;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    ResponseEntity<String> addNewRole(Roles role);

    List<Roles> GetRoles();

    ResponseEntity<Roles> getRolesById(long roleId);

    ResponseEntity<String> deleteRole(long roleId);

    ResponseEntity updateRole(long roleId, Roles upd_role);
}
