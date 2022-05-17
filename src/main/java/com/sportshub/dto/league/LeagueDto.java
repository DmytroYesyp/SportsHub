package com.sportshub.dto.league;
import com.sportshub.dto.team.TeamDto;
import lombok.Data;

import java.util.List;

@Data
public class LeagueDto {
    private Long id;
    private String name;
    private List<TeamDto> teams;
}
