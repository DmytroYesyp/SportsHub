package com.sportshub.service.league;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.league.LeagueCreateDto;
import com.sportshub.dto.league.LeagueDto;
import com.sportshub.dto.league.LeagueUpdateDto;

import java.util.List;

public interface LeagueService {
    LeagueDto create(LeagueCreateDto leagueDto);
    List<LeagueDto> findAll(int page, int limit);
    CountDto getCount();
    LeagueDto find(Long id);
    void update(Long id, LeagueUpdateDto leagueDto);
    void delete(Long id);
}
