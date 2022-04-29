package com.sportshub.repository.role;

import com.sportshub.entity.role.RoleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query("FROM RoleEntity")
    List<RoleEntity> findAllRoles(Pageable pageable);

    @Query(value = """
            SELECT name FROM roles r
            JOIN user_roles ur ON (ur.role_id = r.id)
            WHERE ur.user_id = :userId
            """, nativeQuery = true)
    List<String> findUserRoleNames(@Param("userId") Long userId);

    @Modifying
    @Query("""
            UPDATE RoleEntity SET 
                name = :#{#entity.name}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") RoleEntity entity);

    @Modifying
    @Query("DELETE FROM RoleEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
