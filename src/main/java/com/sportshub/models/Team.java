package com.sportshub.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String coach;
    private String state;

    @ManyToMany
    @JoinTable(
            name = "team_news",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id"))
    @JsonIgnoreProperties("team")
    Set<News> news;

    public Team(Long id, String name, String coach, String state, Set<News> news) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.state = state;
        this.news = news;
    }

    public Team() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }
}
