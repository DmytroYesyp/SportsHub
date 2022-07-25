package com.sportshub.service.newsletter;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.newsletter.NewsletterCreateDto;
import com.sportshub.dto.newsletter.NewsletterDto;
import com.sportshub.dto.newsletter.NewsletterUpdateDto;


import java.util.List;

public interface NewsletterService {
    NewsletterDto create(NewsletterCreateDto newsletterDto);
    List<NewsletterDto> findAll(Long leagueId);
    CountDto getCount();
    NewsletterDto find(Long id);
    void update(Long id, NewsletterUpdateDto newsletterDto);
    void delete(Long id, Long leagueId);
}