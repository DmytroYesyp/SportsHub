package com.sportshub.repository.league;

import com.sportshub.entity.league.LeagueEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {

    @Query("FROM LeagueEntity")
    List<LeagueEntity> findAllLeagues(Pageable pageable);

    @Modifying
    @Query("""
            UPDATE LeagueEntity SET 
                name = :#{#entity.name},
                sportKind = :#{#entity.sportKind}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") LeagueEntity entity);

    @Modifying
    @Query("DELETE FROM LeagueEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
