package com.sportshub.service.follows;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.follows.FollowsCreateDto;
import com.sportshub.dto.follows.FollowsDto;
import com.sportshub.dto.follows.FollowsUpdateDto;


import java.util.List;

public interface FollowsService {
    FollowsDto create(FollowsCreateDto followsDto);
    List<FollowsDto> findAll(Long userId);
    CountDto getCount();
    FollowsDto find(Long id);
    void update(Long id, FollowsUpdateDto followsDto);
    void delete(Long id, Long teamId);
}