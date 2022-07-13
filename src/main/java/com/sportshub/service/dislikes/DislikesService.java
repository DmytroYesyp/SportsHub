package com.sportshub.service.dislikes;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.dislikes.DislikesCreateDto;
import com.sportshub.dto.dislikes.DislikesDto;
import com.sportshub.dto.dislikes.DislikesUpdateDto;


import java.util.List;

public interface DislikesService {
    DislikesDto create(DislikesCreateDto dislikesDto);
    List<DislikesDto> findAll();
    CountDto getCount(Long commId);
    CountDto getCheck(Long commId, Long userId);
    DislikesDto find(Long id);
    void update(Long id, DislikesUpdateDto dislikesDto);
    void delete(Long id, Long userId);
}