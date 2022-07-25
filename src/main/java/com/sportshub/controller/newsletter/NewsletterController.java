package com.sportshub.controller.newsletter;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.newsletter.NewsletterCreateDto;
import com.sportshub.dto.newsletter.NewsletterDto;
import com.sportshub.dto.newsletter.NewsletterUpdateDto;
import com.sportshub.service.newsletter.NewsletterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("newsletter")
public class NewsletterController {

    private final NewsletterService newsletterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsletterDto create(@RequestBody @Valid NewsletterCreateDto newsletterDto) {
        return newsletterService.create(newsletterDto);
    }

    @GetMapping
    public List<NewsletterDto> findAll(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "1000") int limit,
                                       @RequestParam(defaultValue = "0") Long leagueId) {

        return newsletterService.findAll(leagueId);
    }

    @GetMapping("{id}")
    public NewsletterDto find(@PathVariable Long id) {
        return newsletterService.find(id);
    }

    @GetMapping("count")
    public CountDto getCount() {
        return newsletterService.getCount();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody NewsletterUpdateDto newsletterDto) {
        newsletterService.update(id, newsletterDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @RequestParam(defaultValue = "0") Long leagueId) {
        newsletterService.delete(id, leagueId);
    }

}