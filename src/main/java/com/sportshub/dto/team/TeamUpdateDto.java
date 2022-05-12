package com.sportshub.dto.team;
import lombok.Data;

@Data
public class TeamUpdateDto {
    private String name;
    private String coach;
    private String image_url;
    private String state;
}
