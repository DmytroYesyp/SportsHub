package com.sportshub.dto.league;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class LeagueCreateDto {
    @NotBlank
    private String name;
    private Integer leagueDate;
}
