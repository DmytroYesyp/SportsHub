package com.sportshub.service.page.content.impl;


import com.sportshub.dto.page.content.PageContentCreateDto;
import com.sportshub.dto.page.content.PageContentDto;
import com.sportshub.dto.page.content.PageContentUpdateDto;
import com.sportshub.dto.team.TeamDto;
import com.sportshub.entity.page.content.PageContentEntity;
import com.sportshub.entity.team.TeamEntity;
import com.sportshub.exception.ConflictException;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.page.content.PageContentMapper;
import com.sportshub.repository.page.content.PageContentRepository;
import com.sportshub.service.page.content.PageContentService;
import org.hibernate.exception.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class PageContentServiceImpl implements PageContentService {

    private final PageContentRepository pageContentRepository;
    private final PageContentMapper pageContentMapper;

    @Override
    public PageContentDto create(PageContentCreateDto pageContentDto) {
        PageContentEntity pageContentEntity = pageContentMapper.toEntity(pageContentDto);
        try {
            pageContentEntity = pageContentRepository.save(pageContentEntity);
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("page_content_text_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("PageContent with the text '%s' already exists!", pageContentEntity.getText());
                    throw new ConflictException(errorMessage);
                }
            }

            throw e;
        }
        return pageContentMapper.toDto(pageContentEntity);
    }

    @Override
    public List<PageContentDto> findAll(int page, int limit) {
        List<PageContentEntity> pageContentEntities = pageContentRepository.findAllPageContent(PageRequest.of(page - 1, limit));
        return pageContentMapper.toDtoList(pageContentEntities);
    }

    @Override
    public PageContentDto find(Long id) {
        PageContentEntity pageContentEntity = pageContentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("PageContent not found!"));
        return pageContentMapper.toDto(pageContentEntity);
    }

    @Override
    @Transactional
    public void update(Long id, PageContentUpdateDto pageContentDto) {

        PageContentEntity pageContentEntity = pageContentMapper.toEntity(pageContentDto);
        try{
            int affectedRaws = pageContentRepository.update(id, pageContentEntity);
            if (affectedRaws == 0) {
                new NotFoundException("PageContent not found!");
            }
        } catch (DataIntegrityViolationException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                if ("page_content_text_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                    String errorMessage = String.format("PageContent with text '%s' already exists!", pageContentEntity.getText());
                    throw new ConflictException(errorMessage);
                }
            }
            throw e;
        }
        pageContentRepository.update(id, pageContentEntity);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = pageContentRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("PageContent not found!");
        }
    }
}
