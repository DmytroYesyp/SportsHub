package com.sportshub.controllers;

import java.util.List;
import java.util.Optional;

import com.sportshub.models.News;
import com.sportshub.repo.NewsRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class NewsController {

    private final NewsRepository repository;

    NewsController(NewsRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/news")
    List<News> all() {
        return repository.findAll();
    }

    @PostMapping("/news")
    News newEmployee(@RequestBody News newNews) {
        return repository.save(newNews);
    }


    @GetMapping("/news/{id}")
    Optional<News> one(@PathVariable Long id) {

        return repository.findById(id);

    }

    @PutMapping("/news/{id}")
    News replaceEmployee(@RequestBody News newNews, @PathVariable Long id) {

        return repository.findById(id)
                .map(news -> {
                    news.setTitle(newNews.getTitle());
                    news.setDescription(newNews.getDescription());
                    news.setImage(newNews.getImage());
                    news.setLeague_id(newNews.getLeague_id());
                    return repository.save(news);
                })
                .orElseGet(() -> {
                    newNews.setId(id);
                    return repository.save(newNews);
                });
    }

    @DeleteMapping("/news/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
