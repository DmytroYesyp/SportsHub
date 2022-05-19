package com.sportshub.repository.team;

import com.sportshub.entity.team.TeamEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    @Query("FROM TeamEntity")
    List<TeamEntity> findAllTeams(Pageable pageable);

    @Modifying
    @Query("""
            UPDATE TeamEntity SET 
                name = :#{#entity.name},
                league = :#{#entity.league},
                coach =:#{#entity.coach},
                image_url =:#{#entity.image_url},
                state =:#{#entity.state}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") TeamEntity entity);

    @Modifying
    @Query("DELETE FROM TeamEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
