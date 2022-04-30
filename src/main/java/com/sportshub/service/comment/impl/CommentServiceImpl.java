package com.sportshub.service.comment.impl;

import com.sportshub.dto.comment.CommentCreateDto;
import com.sportshub.dto.comment.CommentDto;
import com.sportshub.dto.comment.CommentUpdateDto;
import com.sportshub.dto.count.CountDto;
import com.sportshub.entity.comment.CommentEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.comment.CommentMapper;
import com.sportshub.repository.comment.CommentRepository;
import com.sportshub.service.comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto create(CommentCreateDto commentDto) {
        CommentEntity commentEntity = commentMapper.toEntity(commentDto);

        commentEntity = commentRepository.save(commentEntity);

        return commentMapper.toDto(commentEntity);
    }

    @Override
    public List<CommentDto> findAll(int page, int limit) {
        List<CommentEntity> commentEntities = commentRepository.findAllComments(PageRequest.of(page - 1, limit));
        return commentMapper.toDtoList(commentEntities);
    }

    @Override
    public CountDto getCount() {
        int sportKindsCount = (int) commentRepository.count();
        return new CountDto(sportKindsCount);
    }

    @Override
    public CommentDto find(Long id) {
        CommentEntity commentEntity = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found!"));
        return commentMapper.toDto(commentEntity);
    }

    @Override
    @Transactional
    public void update(Long id, CommentUpdateDto commentDto) {
        CommentEntity commentEntity = commentMapper.toEntity(commentDto);
        int affectedRaws = commentRepository.update(id, commentEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Comment not found!");
        }
        commentRepository.update(id, commentEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = commentRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Comment not found!");
        }
    }
}
