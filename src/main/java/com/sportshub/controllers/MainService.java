package com.sportshub.controllers;


import com.sportshub.security.entities.News;
import com.sportshub.security.entities.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    private final NewsRepository newsRepository;

    @Autowired
    public MainService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getNews() {
        return newsRepository.findAll();
    }
}
