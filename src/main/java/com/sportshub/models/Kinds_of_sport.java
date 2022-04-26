//package com.sportshub.models;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//public class Kinds_of_sport {
//    private @Id
//    @GeneratedValue
//    Long id;
//    private String name;
//
//    @ManyToMany
//    @JoinTable(
//            name = "news_kind_of_sport",
//            joinColumns = @JoinColumn(name = "kinds_of_sport_id"),
//            inverseJoinColumns = @JoinColumn(name = "news_id"))
//    @JsonIgnoreProperties("kinds_of_sport")
//    Set<News> news;
//
//    public Kinds_of_sport(Long id, String name, Set<News> news) {
//        this.id = id;
//        this.name = name;
//        this.news = news;
//    }
//
//    public Kinds_of_sport() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<News> getNews() {
//        return news;
//    }
//
//    public void setNews(Set<News> news) {
//        this.news = news;
//    }
//}
