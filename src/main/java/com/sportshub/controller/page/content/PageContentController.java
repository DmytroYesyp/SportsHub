package com.sportshub.controller.page.content;


import com.sportshub.dto.page.content.PageContentCreateDto;
import com.sportshub.dto.page.content.PageContentDto;
import com.sportshub.dto.page.content.PageContentUpdateDto;
import com.sportshub.service.page.content.PageContentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/page-content")
public class PageContentController {

    private final PageContentService pageContentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PageContentDto create(@RequestBody @Valid PageContentCreateDto pageContentDto) {
        return pageContentService.create(pageContentDto);
    }

    @GetMapping
    public List<PageContentDto> findAll(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "1000") int limit) {
        return pageContentService.findAll(page, limit);
    }

    @GetMapping("/{id}")
    public PageContentDto find(@PathVariable Long id) {
        return pageContentService.find(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody PageContentUpdateDto pageContentDto) {
        Long id = pageContentDto.getId();
        pageContentService.update(id, pageContentDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pageContentService.delete(id);
    }

}
