package com.sportshub.security.services;
import com.google.gson.Gson;
import com.sportshub.security.entities.Roles;
import com.sportshub.security.entities.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class RolesService {
    private final RoleRepository roleRepository;

    @Autowired
    public RolesService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<String> addNewRole(Roles role) {
        roleRepository.save(role);
        return ResponseEntity.ok(new Gson().toJson(role));
    }

    public List<Roles> GetRoles() {
        return roleRepository.findAll();
    }

    public ResponseEntity<Roles> getRolesById(long roleId) {
        boolean exists = roleRepository.existsById(roleId);
        if (!exists) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleRepository.findById(roleId).get());
    }

    public ResponseEntity<String> deleteRole(long roleId) {
        roleRepository.deleteById(roleId);
        return ResponseEntity.ok(new Gson().toJson(roleId));
    }

    public ResponseEntity updateRole(long roleId, Roles upd_role) {
        if (roleRepository.findById(roleId).isEmpty())
            return ResponseEntity.notFound().build();
        Roles roles = roleRepository.getById(roleId);
        if (upd_role.getName() != null && upd_role.getName().length() > 0 && !Objects.equals(roles.getName(), upd_role.getName())) {
            roles.setName(upd_role.getName());
        }
        roleRepository.save(roles);
        return ResponseEntity.ok().body(null);
    }
}
