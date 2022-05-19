package com.sportshub.service.team;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.team.TeamCreateDto;
import com.sportshub.dto.team.TeamDto;
import com.sportshub.dto.team.TeamUpdateDto;


import java.util.List;

public interface TeamService {
    TeamDto create(TeamCreateDto teamDto);
    List<TeamDto> findAll(int page, int limit);
    CountDto getCount();
    TeamDto find(Long id);
    void update(Long id, TeamUpdateDto teamDto);
    void delete(Long id);
}
