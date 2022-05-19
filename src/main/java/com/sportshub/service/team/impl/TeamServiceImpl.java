package com.sportshub.service.team.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.team.TeamCreateDto;
import com.sportshub.dto.team.TeamDto;
import com.sportshub.dto.team.TeamUpdateDto;
import com.sportshub.entity.team.TeamEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.team.TeamMapper;
import com.sportshub.repository.team.TeamRepository;
import com.sportshub.service.team.TeamService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public TeamDto create(TeamCreateDto teamDto) {
        TeamEntity teamEntity = teamMapper.toEntity(teamDto);
        try {
            teamEntity = teamRepository.save(teamEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("team_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Team with name '%s' already exists!", teamEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return teamMapper.toDto(teamEntity);
    }

    @Override
    public List<TeamDto> findAll(int page, int limit) {
        List<TeamEntity> sportKindEntities = teamRepository.findAllTeams(PageRequest.of(page - 1, limit));
        return teamMapper.toDtoList(sportKindEntities);
    }

    @Override
    public CountDto getCount() {
        int sportKindsCount = (int) teamRepository.count();
        return new CountDto(sportKindsCount);
    }

    @Override
    public TeamDto find(Long id) {
        TeamEntity sportKindEntity = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Team not found!"));
        return teamMapper.toDto(sportKindEntity);
    }

    @Override
    @Transactional
    public void update(Long id, TeamUpdateDto teamDto) {
        TeamEntity teamEntity = teamMapper.toEntity(teamDto);
        try {
            int affectedRaws = teamRepository.update(id, teamEntity);
            if (affectedRaws == 0) {
                new NotFoundException("Team not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("team_name_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Sport kind with name '%s' already exists!", teamEntity.getName());
                    throw new ConflictException(errorMessage);
                }
            }
            throw e;
        }

        teamRepository.update(id, teamEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = teamRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Team not found!");
        }
    }
}
