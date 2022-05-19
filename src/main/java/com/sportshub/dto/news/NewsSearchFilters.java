package com.sportshub.dto.news;

import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class NewsSearchFilters {
    private String title;
    private String description;
    private String text;
    private Instant publicationDateFrom;
    private Instant publicationDateTo;
    private Set<Long> leagueIds;
    private Set<Long> kindsOfSportIds;
    private Set<Long> teamIds;
}
