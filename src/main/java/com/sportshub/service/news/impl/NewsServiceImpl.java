package com.sportshub.service.news.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.news.NewsCreateDto;
import com.sportshub.dto.news.NewsDto;
import com.sportshub.dto.news.NewsSearchFilters;
import com.sportshub.dto.news.NewsUpdateDto;
import com.sportshub.dto.payload.BooleanPayload;
import com.sportshub.dto.payload.IntegerPayload;
import com.sportshub.entity.news.NewsEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.mapper.news.NewsMapper;
import com.sportshub.repository.news.NewsRepository;
import com.sportshub.service.news.NewsService;
import com.sportshub.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsDto create(NewsCreateDto newsDto) {
        NewsEntity newsEntity = newsMapper.toEntity(newsDto);
        try {
            newsEntity = newsRepository.save(newsEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("news_title_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("News with title '%s' already exists!", newsEntity.getTitle());
                    throw new ConflictException(errorMessage);
                } else if ("news_league_id_fkey".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Not find a suitable league! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                } else if ("news_kind_of_sport_kinds_of_sport_id_fkey".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Not find a suitable kind of sports! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                } else if ("team_news_team_id_fkey".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("Not find a suitable team! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return newsMapper.toDto(newsEntity);
    }

    @Override
    public List<NewsDto> findAll(NewsSearchFilters newsSearchFilters, int limit, Integer page) {
         List<NewsEntity> newsEntities = newsRepository.findAll(newsSearchFilters, limit, page);
        return newsMapper.toDtoList(newsEntities);
    }

    @Override
    public List<NewsDto> findPopular(int limitDate) {
        List<NewsEntity> newsEntities = newsRepository.findPopular(limitDate, PageRequest.of(0,3));
        return newsMapper.toDtoList(newsEntities);
    }

    @Override
    public List<NewsDto> findCommented(int limitDate) {
        List<NewsEntity> newsEntities = newsRepository.findCommented(limitDate, PageRequest.of(0,3));
        return newsMapper.toDtoList(newsEntities);
    }

    @Override
    public CountDto getCount(NewsSearchFilters newsSearchFilters) {
        int newsCount = newsRepository.count(newsSearchFilters);
        return new CountDto(newsCount);
    }

    @Override
    public NewsDto find(Long id) {
        NewsEntity newsEntity = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("News not found!"));
        return newsMapper.toDto(newsEntity);
    }

    @Override
    @Transactional
    public void update(Long id, NewsUpdateDto newsDto) {
        NewsEntity newsEntity = newsMapper.toEntity(newsDto);
        try{
            int affectedRaws = newsRepository.update(id, newsEntity);
            if (affectedRaws == 0) {
                new NotFoundException("News not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();


            if (cause instanceof ConstraintViolationException) {
                String constraintName = ((ConstraintViolationException) cause).getConstraintName();

                if ("news_title_key".equals(constraintName )) {
                    String errorMessage = String.format("News with title '%s' already exists!", newsEntity.getTitle());
                    throw new ConflictException(errorMessage);
                } else if ("news_league_id_fkey".equals(constraintName)) {
                    String errorMessage = String.format("Not find a suitable league! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                } else if ("news_kind_of_sport_kinds_of_sport_id_fkey".equals(constraintName)) {
                    String errorMessage = String.format("Not find a suitable kind of sports! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                } else if ("team_news_team_id_fkey".equals(constraintName)) {
                    String errorMessage = String.format("Not find a suitable team! Problems with foreign key!");
                    throw new ConflictException(errorMessage);
                }
            }
            throw e;
        }

        newsRepository.update(id, newsEntity);
    }

    @Override
    @Transactional
    public void updatePublicationStatus(Long id, BooleanPayload booleanPayload) {
        int affectedRaws = newsRepository.updatePublicationStatus(id, booleanPayload.getValue());
        if (affectedRaws == 0) {
            throw new NotFoundException("News not found!");
        }
    }

    @Override
    @Transactional
    public void updateMainPageOrder(Long id, IntegerPayload integerPayload) {
        int affectedRaws = newsRepository.updateMainPageOrder(id, integerPayload.getValue());
        if (affectedRaws == 0) {
            throw new NotFoundException("News not found!");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = newsRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("News not found!");
        }
    }
}