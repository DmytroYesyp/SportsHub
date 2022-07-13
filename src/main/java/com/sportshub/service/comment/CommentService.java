package com.sportshub.service.comment;

import com.sportshub.dto.comment.CommentCreateDto;
import com.sportshub.dto.comment.CommentDto;
import com.sportshub.dto.comment.CommentSearchFilters;
import com.sportshub.dto.comment.CommentUpdateDto;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.news.NewsSearchFilters;


import java.util.List;

public interface CommentService {
    CommentDto create(CommentCreateDto commentDto);
    List<CommentDto> findAll(Long id, CommentSearchFilters commentSearchFilters, int page, int limit);
    CountDto getCount();
    CommentDto find(Long id);
    void update(Long id, CommentUpdateDto commentDto);
    void delete(Long id);
}
