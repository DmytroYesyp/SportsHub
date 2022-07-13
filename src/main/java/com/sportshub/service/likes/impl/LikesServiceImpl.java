package com.sportshub.service.likes.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.count.CountLikesDto;
import com.sportshub.dto.likes.LikesCreateDto;
import com.sportshub.dto.likes.LikesDto;
import com.sportshub.dto.likes.LikesUpdateDto;
import com.sportshub.entity.likes.LikesEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.likes.LikesMapper;
import com.sportshub.repository.likes.LikesRepository;
import com.sportshub.service.likes.LikesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;
    private final LikesMapper likesMapper;

    @Override
    public LikesDto create(LikesCreateDto likesDto) {
        LikesEntity likesEntity = likesMapper.toEntity(likesDto);

        likesEntity = likesRepository.save(likesEntity);

        return likesMapper.toDto(likesEntity);
    }

    @Override
    public List<LikesDto> findAll() {
        List<LikesEntity> likesEntities = likesRepository.findAllLikes();
        return likesMapper.toDtoList(likesEntities);
    }

    @Override
    public CountDto getCount(Long commId) {
        int likesCount = (int) likesRepository.count(commId);
        return new CountDto(likesCount);
    }

    @Override
    public CountDto getCheck(Long commId, Long userId) {
        int likesCount = (int) likesRepository.check(commId, userId);
        return new CountDto(likesCount);
    }

    @Override
    public LikesDto find(Long id) {
        LikesEntity likesEntity = likesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Like not found!"));
        return likesMapper.toDto(likesEntity);
    }

    @Override
    @Transactional
    public void update(Long id, LikesUpdateDto likesDto) {
        LikesEntity likesEntity = likesMapper.toEntity(likesDto);
        int affectedRaws = likesRepository.update(id, likesEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Like not found!");
        }
        likesRepository.update(id, likesEntity);
    }

    @Override
    @Transactional
    public void delete(Long id, Long userId) {
        int affectedRaws = likesRepository.removeById(id, userId);
        if (affectedRaws == 0) {
            throw new NotFoundException("Like not found!");
        }
    }
}