package com.sportshub.entity.news;

import com.sportshub.entity.league.LeagueEntity;
import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

@Data
@Entity
@Table(name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long views;
    private String title;
    private String description;
    private String text;
    private Instant publicationDate;
    private String image;

    @ManyToOne
    private LeagueEntity league;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "news_kind_of_sport", joinColumns = @JoinColumn(name = "news_id"))
    @Column(name = "kinds_of_sport_id")
    private Set<Long> kindsOfSportIds;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "team_news", joinColumns = @JoinColumn(name = "news_id"))
    @Column(name = "team_id")
    private Set<Long> teamIds;
}
