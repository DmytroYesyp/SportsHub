package com.sportshub.service.language;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.language.LanguageCreateDto;
import com.sportshub.dto.language.LanguageDto;
import com.sportshub.dto.language.LanguageUpdateDto;


import java.util.List;

public interface LanguageService {
    LanguageDto create(LanguageCreateDto languageDto);
    List<LanguageDto> findAll();
    CountDto getCount();
    LanguageDto find(Long id);
    void update(Long id, LanguageUpdateDto languageDto);
    void delete(Long id);
}