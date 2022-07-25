package com.sportshub.service.newsletter.impl;
import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.newsletter.NewsletterCreateDto;
import com.sportshub.dto.newsletter.NewsletterDto;
import com.sportshub.dto.newsletter.NewsletterUpdateDto;
import com.sportshub.entity.newsletter.NewsletterEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.newsletter.NewsletterMapper;
import com.sportshub.repository.newsletter.NewsletterRepository;
import com.sportshub.service.newsletter.NewsletterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final NewsletterMapper newsletterMapper;

    @Override
    public NewsletterDto create(NewsletterCreateDto newsletterDto) {
        NewsletterEntity newsletterEntity = newsletterMapper.toEntity(newsletterDto);

        newsletterEntity = newsletterRepository.save(newsletterEntity);

        return newsletterMapper.toDto(newsletterEntity);
    }

    @Override
    public List<NewsletterDto> findAll(Long leagueId) {
        List<NewsletterEntity> newsletterEntities = newsletterRepository.findAllNewsletters(leagueId);
        return newsletterMapper.toDtoList(newsletterEntities);
    }

    @Override
    public CountDto getCount() {
        int newsletterCount = (int) newsletterRepository.count();
        return new CountDto(newsletterCount);
    }

    @Override
    public NewsletterDto find(Long id) {
        NewsletterEntity newsletterEntity = newsletterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Newsletter not found!"));
        return newsletterMapper.toDto(newsletterEntity);
    }

    @Override
    @Transactional
    public void update(Long id, NewsletterUpdateDto newsletterDto) {
        NewsletterEntity newsletterEntity = newsletterMapper.toEntity(newsletterDto);
        int affectedRaws = newsletterRepository.update(id, newsletterEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Newsletter not found!");
        }
        newsletterRepository.update(id, newsletterEntity);
    }

    @Override
    @Transactional
    public void delete(Long id, Long leagueId) {
        int affectedRaws = newsletterRepository.removeById(id, leagueId);
        if (affectedRaws == 0) {
            throw new NotFoundException("Newsletter not found!");
        }
    }
}