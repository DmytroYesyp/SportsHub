package com.sportshub.service.sport.kind;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.sport.kind.SportKindContentDto;
import com.sportshub.dto.sport.kind.SportKindDto;
import com.sportshub.dto.sport.kind.SportKindUpdateDto;

import java.util.List;

public interface SportKindService {
    SportKindDto create(SportKindContentDto sportKindDto);
    List<SportKindDto> findAll(int page, int limit);
    CountDto getCount();
    SportKindDto find(Long id);
    void update(Long id, SportKindContentDto sportKindDto);
    void delete(Long id);
}
