package com.sportshub.security.controller;

import com.sportshub.security.services.RolesService;
import com.sportshub.security.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/roles/")
public class RolesController {
    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping(path = "getAllRoles")
    public List<Roles> getUsers() {
        return rolesService.GetRoles();
    }

    @GetMapping(path = "roles{userId}")
    public ResponseEntity<Roles> getUser(@RequestParam long roleId) {
        return rolesService.getRolesById(roleId);
    }

    @DeleteMapping(path = "roles{userId}")
    public ResponseEntity<String> deleteUser(@RequestParam long userId) {
        return rolesService.deleteRole(userId);
    }

    @PostMapping(path = "addNewRole")
    public ResponseEntity<String> regNewUser(@RequestBody Roles role) {

        return rolesService.addNewRole(role);

    }

    @PutMapping(path = "roles{roleId}")
    public ResponseEntity updateUser(
            @RequestBody Roles role,
            @PathVariable("roleId") long roleId) {
        return rolesService.updateRole(roleId, role);
    }
}
