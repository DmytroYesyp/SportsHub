package com.sportshub.repository.follows;
import com.sportshub.entity.follows.FollowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface FollowsRepository extends JpaRepository<FollowsEntity, Long> {
    @Query("FROM FollowsEntity where userId = :userId ORDER BY id")
    List<FollowsEntity> findAllFollows(@Param("userId") Long userId);

    @Modifying
    @Query("""
            UPDATE FollowsEntity SET 
                teamId = :#{#entity.teamId},
                userId = :#{#entity.userId}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") FollowsEntity entity);

    @Modifying
    @Query("DELETE FROM FollowsEntity WHERE userId = :id and teamId = :teamId")
    int removeById(@Param("id") Long id, @Param("teamId") Long teamId);
}