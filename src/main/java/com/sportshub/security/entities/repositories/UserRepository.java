package com.sportshub.security.entities.repositories;
import com.sportshub.security.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmail(String email);
}

