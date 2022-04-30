package com.sportshub.service.league.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.league.LeagueCreateDto;
import com.sportshub.dto.league.LeagueDto;
import com.sportshub.dto.league.LeagueUpdateDto;
import com.sportshub.entity.league.LeagueEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.league.LeagueMapper;
import com.sportshub.repository.league.LeagueRepository;
import com.sportshub.service.league.LeagueService;
import com.sportshub.exception.ConflictException;
import org.hibernate.exception.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;

    @Override
    public LeagueDto create(LeagueCreateDto leagueDto) {
        LeagueEntity leagueEntity = leagueMapper.toEntity(leagueDto);
        try {
            leagueEntity = leagueRepository.save(leagueEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("league_name_league_date_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("League with name '%s' and '%s' year already exists!",
                            leagueEntity.getName(), leagueEntity.getLeagueDate());
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return leagueMapper.toDto(leagueEntity);
    }

    @Override
    public List<LeagueDto> findAll(int page, int limit) {
        List<LeagueEntity> leagueEntities = leagueRepository.findAllLeagues(PageRequest.of(page - 1, limit));
        return leagueMapper.toDtoList(leagueEntities);
    }

    @Override
    public CountDto getCount() {
        int leagueCount = (int) leagueRepository.count();
        return new CountDto(leagueCount);
    }

    @Override
    public LeagueDto find(Long id) {
        LeagueEntity leagueEntity = leagueRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("League not found!"));
        return leagueMapper.toDto(leagueEntity);
    }

    @Override
    @Transactional
    public void update(Long id, LeagueUpdateDto leagueDto) {
        LeagueEntity leagueEntity = leagueMapper.toEntity(leagueDto);
        try {
            int affectedRaws = leagueRepository.update(id, leagueEntity);
            if (affectedRaws == 0) {
                new NotFoundException("League not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("league_name_league_date_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("League with name '%s' and '%s' year already exists!",
                            leagueEntity.getName(), leagueEntity.getLeagueDate());
                    throw new ConflictException(errorMessage);
                }
            }
            throw e;
        }

        leagueRepository.update(id, leagueEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = leagueRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("League not found!");
        }
    }
}
