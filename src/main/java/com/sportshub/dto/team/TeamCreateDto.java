package com.sportshub.dto.team;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class TeamCreateDto {
    @NotBlank
    private String name;

    @NotBlank
    private String coach;

    @NotBlank
    private String image_url;

    @NotBlank
    private String state;
}
