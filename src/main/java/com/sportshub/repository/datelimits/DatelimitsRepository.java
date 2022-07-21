package com.sportshub.repository.datelimits;
import com.sportshub.entity.datelimits.DatelimitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface DatelimitsRepository extends JpaRepository<DatelimitsEntity, Long> {
    @Query("FROM DatelimitsEntity ORDER BY id")
    List<DatelimitsEntity> findAllDatelimits();

    @Modifying
    int update(@Param("id") Long id, @Param("entity") DatelimitsEntity entity);

    @Modifying
    @Query("DELETE FROM DatelimitsEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
