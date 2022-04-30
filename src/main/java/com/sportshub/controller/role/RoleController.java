package com.sportshub.controller.role;

import com.sportshub.entity.role.Roles;
import com.sportshub.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/roles/")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping(path = "getAllRoles")
    public List<Roles> getUsers() {
        return roleService.GetRoles();
    }

    @GetMapping(path = "roles{userId}")
    public ResponseEntity<Roles> getUser(@RequestParam long roleId) {
        return roleService.getRolesById(roleId);
    }

    @DeleteMapping(path = "roles{userId}")
    public ResponseEntity<String> deleteUser(@RequestParam long userId) {
        return roleService.deleteRole(userId);
    }

    @PostMapping(path = "addNewRole")
    public ResponseEntity<String> regNewUser(@RequestBody Roles role) {

        return roleService.addNewRole(role);
    }

    @PutMapping(path = "roles{roleId}")
    public ResponseEntity updateUser(
            @RequestBody Roles role,
            @PathVariable("roleId") long roleId) {
        return roleService.updateRole(roleId, role);
    }
}