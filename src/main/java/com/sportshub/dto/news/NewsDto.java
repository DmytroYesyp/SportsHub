package com.sportshub.dto.news;


import java.time.Instant;
import java.util.Set;

import lombok.Data;

@Data
public class NewsDto {
    private Long id;
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
