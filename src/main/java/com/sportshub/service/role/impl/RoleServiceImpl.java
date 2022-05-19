package com.sportshub.service.role.impl;
import com.google.gson.Gson;
import com.sportshub.entity.role.Roles;
import com.sportshub.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements com.sportshub.service.role.RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<String> addNewRole(Roles role) {
        roleRepository.save(role);
        return ResponseEntity.ok(new Gson().toJson(role));
    }

    @Override
    public List<Roles> GetRoles() {
        return roleRepository.findAll();
    }

    @Override
    public ResponseEntity<Roles> getRolesById(long roleId) {
        boolean exists = roleRepository.existsById(roleId);
        if (!exists) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roleRepository.findById(roleId).get());
    }

    @Override
    public ResponseEntity<String> deleteRole(long roleId) {
        roleRepository.deleteById(roleId);
        return ResponseEntity.ok(new Gson().toJson(roleId));
    }

    @Override
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