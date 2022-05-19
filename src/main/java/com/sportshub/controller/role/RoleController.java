package com.sportshub.controller.role;

import com.sportshub.entity.role.Roles;
import com.sportshub.service.role.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/roles/")
public class RoleController {

    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }


    @GetMapping(path = "getAllRoles")
    public List<Roles> getUsers() {
        return roleServiceImpl.GetRoles();
    }

    @GetMapping(path = "roles{userId}")
    public ResponseEntity<Roles> getUser(@RequestParam long roleId) {
        return roleServiceImpl.getRolesById(roleId);
    }

    @DeleteMapping(path = "roles{userId}")
    public ResponseEntity<String> deleteUser(@RequestParam long userId) {
        return roleServiceImpl.deleteRole(userId);
    }

    @PostMapping(path = "addNewRole")
    public ResponseEntity<String> regNewUser(@RequestBody Roles role) {

        return roleServiceImpl.addNewRole(role);
    }

    @PutMapping(path = "roles{roleId}")
    public ResponseEntity updateUser(
            @RequestBody Roles role,
            @PathVariable("roleId") long roleId) {
        return roleServiceImpl.updateRole(roleId, role);
    }
}