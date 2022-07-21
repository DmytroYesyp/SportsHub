package com.sportshub.repository.comment;

import com.sportshub.entity.comment.CommentEntity;
import com.sportshub.entity.news.NewsEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CustomCommentRepository, JpaRepository<CommentEntity, Long> {

    @Query("FROM CommentEntity WHERE newsId = :id ORDER BY id")
    List<CommentEntity> findAllComments(@Param("id") Long id, Pageable pageable);

    @Modifying
    int update(@Param("id") Long id, @Param("entity") CommentEntity entity);

    @Modifying
    @Query("DELETE FROM CommentEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
