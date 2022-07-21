package com.sportshub.repository.sport.kind;

import com.sportshub.entity.sport.kind.SportKindEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SportKindRepository extends JpaRepository<SportKindEntity, Long> {

    @Query("FROM SportKindEntity")
    List<SportKindEntity> findAllSportKinds(Pageable pageable);

    @Modifying
    @Query("""
            UPDATE SportKindEntity SET
                name = :#{#entity.name}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") SportKindEntity entity);

    @Modifying
    @Query("DELETE FROM SportKindEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
