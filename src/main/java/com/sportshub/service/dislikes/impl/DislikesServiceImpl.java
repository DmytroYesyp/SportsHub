package com.sportshub.service.dislikes.impl;
import com.sportshub.dto.count.CountDto;

import com.sportshub.dto.dislikes.DislikesCreateDto;
import com.sportshub.dto.dislikes.DislikesDto;
import com.sportshub.dto.dislikes.DislikesUpdateDto;
import com.sportshub.entity.dislikes.DislikesEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.dislikes.DislikesMapper;
import com.sportshub.repository.dislikes.DislikesRepository;
import com.sportshub.service.dislikes.DislikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DislikesServiceImpl implements DislikesService {

    private final DislikesRepository dislikesRepository;
    private final DislikesMapper dislikesMapper;

    @Override
    public DislikesDto create(DislikesCreateDto dislikesDto) {
        DislikesEntity dislikesEntity = dislikesMapper.toEntity(dislikesDto);

        dislikesEntity = dislikesRepository.save(dislikesEntity);

        return dislikesMapper.toDto(dislikesEntity);
    }

    @Override
    public List<DislikesDto> findAll() {
        List<DislikesEntity> dislikesEntities = dislikesRepository.findAllDislikes();
        return dislikesMapper.toDtoList(dislikesEntities);
    }

    @Override
    public CountDto getCount(Long commId) {
        int dislikesCount = (int) dislikesRepository.count(commId);
        return new CountDto(dislikesCount);
    }

    @Override
    public CountDto getCheck(Long commId, Long userId) {
        int dislikesCount = (int) dislikesRepository.check(commId, userId);
        return new CountDto(dislikesCount);
    }

    @Override
    public DislikesDto find(Long id) {
        DislikesEntity dislikesEntity = dislikesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dislike not found!"));
        return dislikesMapper.toDto(dislikesEntity);
    }

    @Override
    @Transactional
    public void update(Long id, DislikesUpdateDto dislikesDto) {
        DislikesEntity dislikesEntity = dislikesMapper.toEntity(dislikesDto);
        int affectedRaws = dislikesRepository.update(id, dislikesEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Dislike not found!");
        }
        dislikesRepository.update(id, dislikesEntity);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        int affectedRaws = dislikesRepository.removeById(id, userId);
        if (affectedRaws == 0) {
            throw new NotFoundException("Dislike not found!");
        }
    }
}