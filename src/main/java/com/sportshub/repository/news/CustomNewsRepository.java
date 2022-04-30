package com.sportshub.repository.news;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.news.NewsSearchFilters;
import com.sportshub.entity.news.NewsEntity;

import java.util.List;

public interface CustomNewsRepository {
    List<NewsEntity> findAll(NewsSearchFilters newsSearchFilters, int limit, Integer page);
    int count(NewsSearchFilters newsSearchFilters);
}
