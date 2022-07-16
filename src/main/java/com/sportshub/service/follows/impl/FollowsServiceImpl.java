package com.sportshub.service.follows.impl;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.follows.FollowsCreateDto;
import com.sportshub.dto.follows.FollowsDto;
import com.sportshub.dto.follows.FollowsUpdateDto;
import com.sportshub.entity.follows.FollowsEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.follows.FollowsMapper;
import com.sportshub.repository.follows.FollowsRepository;
import com.sportshub.service.follows.FollowsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowsServiceImpl implements FollowsService {

    private final FollowsRepository followsRepository;
    private final FollowsMapper followsMapper;

    @Override
    public FollowsDto create(FollowsCreateDto followsDto) {
        FollowsEntity followsEntity = followsMapper.toEntity(followsDto);

        followsEntity = followsRepository.save(followsEntity);

        return followsMapper.toDto(followsEntity);
    }

    @Override
    public List<FollowsDto> findAll(Long userId) {
        List<FollowsEntity> followsEntities = followsRepository.findAllFollows(userId);
        return followsMapper.toDtoList(followsEntities);
    }

    @Override
    public CountDto getCount() {
        int followsCount = (int) followsRepository.count();
        return new CountDto(followsCount);
    }

    @Override
    public FollowsDto find(Long id) {
        FollowsEntity followsEntity = followsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Follow not found!"));
        return followsMapper.toDto(followsEntity);
    }

    @Override
    @Transactional
    public void update(Long id, FollowsUpdateDto followsDto) {
        FollowsEntity followsEntity = followsMapper.toEntity(followsDto);
        int affectedRaws = followsRepository.update(id, followsEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Follow not found!");
        }
        followsRepository.update(id, followsEntity);
    }

    @Override
    @Transactional
    public void delete(Long id, Long teamId) {
        int affectedRaws = followsRepository.removeById(id, teamId);
        if (affectedRaws == 0) {
            throw new NotFoundException("Follow not found!");
        }
    }
}