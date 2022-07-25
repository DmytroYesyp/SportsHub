package com.sportshub.service.page.content;

import com.sportshub.dto.page.content.PageContentCreateDto;
import com.sportshub.dto.page.content.PageContentDto;
import com.sportshub.dto.page.content.PageContentUpdateDto;

import java.util.List;

public interface PageContentService {
    PageContentDto create(PageContentCreateDto pageContentCreateDto);
    List<PageContentDto> findAll(int page, int limit);
    PageContentDto find(Long id);
    void update(Long id, PageContentUpdateDto pageContentDto);
    void delete(Long id);
}
