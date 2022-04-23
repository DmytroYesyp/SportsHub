package com.sportshub.service.sport.kind.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.sport.kind.SportKindCreateDto;
import com.sportshub.dto.sport.kind.SportKindDto;
import com.sportshub.dto.sport.kind.SportKindUpdateDto;
import com.sportshub.entity.sport.kind.SportKindEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.sport.kind.SportKindMapper;
import com.sportshub.repository.pagination.OffsetBasedPageRequest;
import com.sportshub.repository.sport.kind.SportKindRepository;
import com.sportshub.service.sport.kind.SportKindService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
public class SportKindServiceImpl implements SportKindService {

    private final SportKindRepository sportKindRepository;
    private final SportKindMapper sportKindMapper;

    @Override
    public SportKindDto create(SportKindCreateDto sportKindDto) {
        SportKindEntity sportKindEntity = sportKindMapper.toEntity(sportKindDto);

        sportKindEntity = sportKindRepository.save(sportKindEntity);

        return sportKindMapper.toDto(sportKindEntity);
    }

    @Override
    public List<SportKindDto> findAll(int limit, int offset) {
        List<SportKindEntity> sportKindEntities = sportKindRepository.findAllSportKinds(new OffsetBasedPageRequest(offset, limit));
        return sportKindMapper.toDtoList(sportKindEntities);
    }

    @Override
    public CountDto getCount() {
        int sportKindsCount = (int) sportKindRepository.count();
        return new CountDto(sportKindsCount);
    }

    @Override
    public SportKindDto find(Long id) {
        SportKindEntity sportKindEntity = sportKindRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sport kind not found!"));
        return sportKindMapper.toDto(sportKindEntity);
    }

    @Override
    @Transactional
    public void update(Long id, SportKindUpdateDto sportKindDto) {
        SportKindEntity sportKindEntity = sportKindMapper.toEntity(sportKindDto);
        int affectedRaws = sportKindRepository.update(id, sportKindEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Model not found!");
        }

        sportKindRepository.update(id, sportKindEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = sportKindRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Sport kind not found!");
        }
    }
}
