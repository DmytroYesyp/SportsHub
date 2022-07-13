package com.sportshub.service.datelimits;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.datelimits.DatelimitsCreateDto;
import com.sportshub.dto.datelimits.DatelimitsDto;
import com.sportshub.dto.datelimits.DatelimitsUpdateDto;


import java.util.List;

public interface DatelimitsService {
    DatelimitsDto create(DatelimitsCreateDto datelimitsDto);
    List<DatelimitsDto> findAll();
    CountDto getCount();
    DatelimitsDto find(Long id);
    void update(Long id, DatelimitsUpdateDto datelimitsDto);
    void delete(Long id);
}