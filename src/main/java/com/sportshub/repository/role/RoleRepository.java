package com.sportshub.repository.role;
import com.sportshub.entity.role.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RoleRepository")
public interface RoleRepository extends JpaRepository<Roles, Long> {}
