package com.sportshub.repository.likes;
import com.sportshub.entity.likes.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface LikesRepository extends JpaRepository<LikesEntity, Long> {
    @Query("FROM LikesEntity ORDER BY id")
    List<LikesEntity> findAllLikes();

    @Modifying
    int update(@Param("id") Long id, @Param("entity") LikesEntity entity);

    @Modifying
    @Query("DELETE FROM LikesEntity WHERE commentId = :id and userId = :userId")
    int removeById(@Param("id") Long id, @Param("userId") Long userId);

    @Query(value = "select coalesce ((select cnt from(select comment_id, count (*) as cnt from likes where comment_id = :commId group by comment_id) as cnt), 0)", nativeQuery = true)
    int count(@Param("commId") Long commId);

    @Query(value = "select coalesce ((select cnt from(select comment_id, count (*) as cnt from likes where comment_id = :commId and user_id = :userId group by comment_id) as cnt), 0)", nativeQuery = true)
    int check(@Param("commId") Long commId, @Param("userId") Long userId);
}
