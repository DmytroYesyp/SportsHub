package com.sportshub.repository.comment;

import com.sportshub.entity.comment.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("FROM CommentEntity")
    List<CommentEntity> findAllComments(Pageable pageable);

    @Modifying
    @Query("""
            UPDATE CommentEntity SET 
                text = :#{#entity.text}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") CommentEntity entity);

    @Modifying
    @Query("DELETE FROM CommentEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
