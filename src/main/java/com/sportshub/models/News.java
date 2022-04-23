package com.sportshub.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class News {

    private @Id
    @GeneratedValue
    Long id;
    private String title;
    private String description;
    private Timestamp publication_date = new Timestamp(System.currentTimeMillis());
    private String image;
    Long league_id;

    @ManyToMany(mappedBy = "news")
    @JsonIgnoreProperties("news")
    Set<Kinds_of_sport> kinds_of_sport;

    @ManyToMany(mappedBy = "news")
    @JsonIgnoreProperties("news")
    Set<Team> team;

    public News(Long id, String title, String description, Timestamp publication_date, String image, Long league_id, Set<Kinds_of_sport> kinds_of_sport, Set<Team> team) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publication_date = publication_date;
        this.image = image;
        this.league_id = league_id;
        this.kinds_of_sport = kinds_of_sport;
        this.team = team;
    }

    public News() {}

    public Set<Kinds_of_sport> getKinds_of_sport() {
        return kinds_of_sport;
    }

    public Set<Team> getTeam() {
        return team;
    }

    public void setTeam(Set<Team> team) {
        this.team = team;
    }

    public void setKinds_of_sport(Set<Kinds_of_sport> kinds_of_sports) {
        this.kinds_of_sport = kinds_of_sports;
    }

    public Timestamp getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Timestamp publication_date) {
        this.publication_date = publication_date;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String role) {
        this.description = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Long league_id) {
        this.league_id = league_id;
    }


}