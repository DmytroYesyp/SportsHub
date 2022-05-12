package com.sportshub.dto.team;
import lombok.Data;

@Data
public class TeamDto {
    private Long id;
    private String name;
    private String coach;
    private String image_url;
    private String state;
}
