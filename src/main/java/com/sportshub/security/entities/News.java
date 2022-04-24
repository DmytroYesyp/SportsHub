package com.sportshub.security.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="\"news\"")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Timestamp publication_date;

    @Column
    private String image;

    @ManyToOne()
    @JoinColumn(name = "league_id", nullable = false)
    @JsonBackReference
    private League league;


    public News(String title, String description, Timestamp publication_date, String image, League league) {
        this.setTitle(title);
        this.setDescription(description);
        this.setPublication_date(publication_date);
        this.setImage(image);
        this.setLeague(league);
    }

    public News() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Timestamp publication_date) {
        this.publication_date = publication_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", publication_date='" + getPublication_date() + '\'' +
                ", image='" + getImage() + '\'' +
                ", league='" + getLeague() + '\'' +
                '}';
    }
}