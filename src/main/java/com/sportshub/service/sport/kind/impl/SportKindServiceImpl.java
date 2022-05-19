package com.sportshub.service.sport.kind.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.sport.kind.SportKindContentDto;
import com.sportshub.dto.sport.kind.SportKindDto;
import com.sportshub.entity.sport.kind.SportKindEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.sport.kind.SportKindMapper;
import com.sportshub.repository.sport.kind.SportKindRepository;
import com.sportshub.service.sport.kind.SportKindService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SportKindServiceImpl implements SportKindService {

    private final SportKindRepository sportKindRepository;
    private final SportKindMapper sportKindMapper;

    @Override
    public SportKindDto create(SportKindContentDto sportKindDto) {
        SportKindEntity sportKindEntity = sportKindMapper.toEntity(sportKindDto);
        try {
            sportKindEntity = sportKindRepository.save(sportKindEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("kinds_of_sport_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Sport kind with name '%s' already exists!", sportKindEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return sportKindMapper.toDto(sportKindEntity);
    }

    @Override
    public List<SportKindDto> findAll(int page, int limit) {
        List<SportKindEntity> sportKindEntities = sportKindRepository.findAllSportKinds(PageRequest.of(page - 1, limit));
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
    public void update(Long id, SportKindContentDto sportKindDto) {
        SportKindEntity sportKindEntity = sportKindMapper.toEntity(sportKindDto);
        try{
            int affectedRaws = sportKindRepository.update(id, sportKindEntity);
            if (affectedRaws == 0) {
                new NotFoundException("Sport kind not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("kinds_of_sport_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Sport kind with name '%s' already exists!", sportKindEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }
            throw e;
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
