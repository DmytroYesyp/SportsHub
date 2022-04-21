package com.sportshub.security.entities.repositories;
import com.sportshub.security.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("RoleRepository")
public interface RoleRepository extends JpaRepository<Roles, Long> {}
