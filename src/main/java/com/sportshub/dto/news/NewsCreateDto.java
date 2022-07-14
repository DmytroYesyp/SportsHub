package com.sportshub.dto.news;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Data
public class NewsCreateDto {
    private Long views;
    private String title;
    private String description;
    private String text;
    private Instant publicationDate;
    private String alternativeText;
    private String caption;
    private String image;
    private Boolean isPublished;
    private Integer mainPageOrder;
    private Long leagueId;
    private Set<Long> kindsOfSportIds;
    private Set<Long> teamIds;
}
