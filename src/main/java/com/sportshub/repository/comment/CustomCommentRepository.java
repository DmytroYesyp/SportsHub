package com.sportshub.repository.comment;

import com.sportshub.dto.comment.CommentSearchFilters;
import com.sportshub.dto.news.NewsSearchFilters;
import com.sportshub.entity.comment.CommentEntity;

import java.util.List;

public interface CustomCommentRepository {
    List<CommentEntity> findAll(CommentSearchFilters commentSearchFilters, int limit, Integer page);
    int count(CommentSearchFilters commentSearchFilters);
}
