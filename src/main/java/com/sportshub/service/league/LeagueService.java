package com.sportshub.service.league;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.league.LeagueContentDto;
import com.sportshub.dto.league.LeagueDto;

import java.util.List;

public interface LeagueService {
    LeagueDto create(LeagueContentDto leagueDto);
    List<LeagueDto> findAll(int page, int limit);
    CountDto getCount();
    LeagueDto find(Long id);
    void update(Long id, LeagueContentDto leagueDto);
    void delete(Long id);
}
