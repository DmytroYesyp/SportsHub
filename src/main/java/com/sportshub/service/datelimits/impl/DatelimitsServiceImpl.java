package com.sportshub.service.datelimits.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.datelimits.DatelimitsCreateDto;
import com.sportshub.dto.datelimits.DatelimitsDto;
import com.sportshub.dto.datelimits.DatelimitsUpdateDto;
import com.sportshub.entity.datelimits.DatelimitsEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.datelimits.DatelimitsMapper;
import com.sportshub.repository.datelimits.DatelimitsRepository;
import com.sportshub.service.datelimits.DatelimitsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DatelimitsServiceImpl implements DatelimitsService {

    private final DatelimitsRepository datelimitsRepository;
    private final DatelimitsMapper datelimitsMapper;

    @Override
    public DatelimitsDto create(DatelimitsCreateDto datelimitsDto) {
        DatelimitsEntity datelimitsEntity = datelimitsMapper.toEntity(datelimitsDto);

        datelimitsEntity = datelimitsRepository.save(datelimitsEntity);

        return datelimitsMapper.toDto(datelimitsEntity);
    }

    @Override
    public List<DatelimitsDto> findAll() {
        List<DatelimitsEntity> datelimitsEntities = datelimitsRepository.findAllDatelimits();
        return datelimitsMapper.toDtoList(datelimitsEntities);
    }

    @Override
    public CountDto getCount() {
        int datelimitsCount = (int) datelimitsRepository.count();
        return new CountDto(datelimitsCount);
    }

    @Override
    public DatelimitsDto find(Long id) {
        DatelimitsEntity datelimitsEntity = datelimitsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Date limit not found!"));
        return datelimitsMapper.toDto(datelimitsEntity);
    }

    @Override
    @Transactional
    public void update(Long id, DatelimitsUpdateDto datelimitsDto) {
        DatelimitsEntity datelimitsEntity = datelimitsMapper.toEntity(datelimitsDto);
        int affectedRaws = datelimitsRepository.update(id, datelimitsEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Date limit not found!");
        }
        datelimitsRepository.update(id, datelimitsEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = datelimitsRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Date limit not found!");
        }
    }
}