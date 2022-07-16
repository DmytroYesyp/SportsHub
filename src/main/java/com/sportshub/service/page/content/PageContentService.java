package com.sportshub.service.page.content;

import com.sportshub.dto.page.content.PageContentDto;
import com.sportshub.dto.page.content.PageContentUpdateDto;


public interface PageContentService {
    PageContentDto find(Long id);
    void update(Long id, PageContentDto pageContentDto);
}
