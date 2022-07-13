package com.sportshub.service.likes;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.likes.LikesCreateDto;
import com.sportshub.dto.likes.LikesDto;
import com.sportshub.dto.likes.LikesUpdateDto;


import java.util.List;

public interface LikesService {
    LikesDto create(LikesCreateDto likesDto);
    List<LikesDto> findAll();
    CountDto getCount(Long commId);
    CountDto getCheck(Long commId, Long userId);
    LikesDto find(Long id);
    void update(Long id, LikesUpdateDto likesDto);
    void delete(Long id, Long userId);
}