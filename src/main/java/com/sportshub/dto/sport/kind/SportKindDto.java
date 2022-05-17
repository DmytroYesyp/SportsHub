package com.sportshub.dto.sport.kind;

import com.sportshub.dto.league.LeagueDto;
import lombok.Data;

import java.util.List;

@Data
public class SportKindDto extends SportKindContentDto {

    private Long id;

    private List<LeagueDto> leagues;

}
