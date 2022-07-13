package com.sportshub.controller.news;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.news.NewsCreateDto;
import com.sportshub.dto.news.NewsDto;
import com.sportshub.dto.news.NewsSearchFilters;
import com.sportshub.dto.news.NewsUpdateDto;
import com.sportshub.service.news.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("news")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto create(@RequestBody @Valid NewsCreateDto newsDto) {
        return newsService.create(newsDto);
    }

    @GetMapping
    public List<NewsDto> findAll(NewsSearchFilters newsSearchFilters,
                                 @RequestParam(defaultValue = "1000") int limit,
                                 @RequestParam(defaultValue = "1") Integer page) {

        return newsService.findAll(newsSearchFilters, limit, page);
    }

    @GetMapping("mostpop")
    public List<NewsDto> findPopular(@RequestParam(defaultValue = "30") int limitDate){
        return newsService.findPopular(limitDate);
    }

    @GetMapping("mostcomm")
    public List<NewsDto> findCommented(@RequestParam(defaultValue = "30") int limitDate){
        return newsService.findCommented(limitDate);
    }

    @GetMapping("{id}")
    public NewsDto find(@PathVariable Long id) {
        return newsService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount(NewsSearchFilters newsSearchFilters) {
        return newsService.getCount(newsSearchFilters);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody NewsUpdateDto newsDto) {
        newsService.update(id, newsDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {newsService.delete(id);}
}