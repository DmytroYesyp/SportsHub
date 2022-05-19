package com.sportshub.dto.team;

import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class TeamCreateDto {
    @NotBlank
    private String name;

    private Long leagueId;

    private String coach;

    private String image_url;

    private String state;
}
