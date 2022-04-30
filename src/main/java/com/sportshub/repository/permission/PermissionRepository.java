package com.sportshub.repository.permission;

import com.sportshub.entity.permission.PermissionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    @Query("FROM PermissionEntity")
    List<PermissionEntity> findAllPermissions(Pageable pageable);

    @Modifying
    @Query("""
            UPDATE PermissionEntity SET 
                name = :#{#entity.name}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") PermissionEntity entity);

    @Modifying
    @Query("DELETE FROM PermissionEntity WHERE id = :id")
    int removeById(@Param("id") Long id);

    @Query(value = """
        SELECT EXISTS (
            SELECT 1 FROM user_roles ur
            JOIN permission_roles pr ON (pr.role_id = ur.role_id)
            JOIN permissions p ON (p.id = pr.permission_id)
            WHERE ur.user_id = :userId AND p.name = :permission
        )""", nativeQuery = true)
    boolean existPermissionForUser(@Param("permission") String permission, @Param("userId") Long userId);
}
