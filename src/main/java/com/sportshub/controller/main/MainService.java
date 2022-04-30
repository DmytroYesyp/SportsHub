package com.sportshub.controller.main;


import com.sportshub.entity.news.NewsEntity;
import com.sportshub.repository.news.NewsRepository;
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

    public List<NewsEntity> getNews() {
        return newsRepository.findAll();
    }
}
