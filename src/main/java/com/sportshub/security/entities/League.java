package com.sportshub.security.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name="\"league\"")
public class League {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Timestamp league_date;

    @OneToMany(mappedBy="league", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<News> news;


    public League(String name, Timestamp league_date, List<News> news) {
        this.setName(name);
        this.setLeague_date(league_date);
        this.setNews(news);
    }

    public League() {

    }

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

    public Timestamp getLeague_date() {
        return league_date;
    }

    public void setLeague_date(Timestamp league_date) {
        this.league_date = league_date;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", league_date='" + getLeague_date() + '\'' +
                ", news='" + getNews() + '\'' +
                '}';
    }
}