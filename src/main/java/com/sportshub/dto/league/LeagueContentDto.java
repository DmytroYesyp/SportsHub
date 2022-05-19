package com.sportshub.dto.league;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class LeagueContentDto {
    @NotBlank
    private String name;
    private Long sportKindId;
}
