package com.sportshub.service.language.impl;

import com.sportshub.dto.count.CountDto;
import com.sportshub.dto.language.LanguageCreateDto;
import com.sportshub.dto.language.LanguageDto;
import com.sportshub.dto.language.LanguageUpdateDto;
import com.sportshub.entity.language.LanguageEntity;
import com.sportshub.exception.NotFoundException;
import com.sportshub.mapper.language.LanguageMapper;
import com.sportshub.repository.language.LanguageRepository;
import com.sportshub.service.language.LanguageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    public LanguageDto create(LanguageCreateDto languageDto) {
        LanguageEntity languageEntity = languageMapper.toEntity(languageDto);

        languageEntity = languageRepository.save(languageEntity);

        return languageMapper.toDto(languageEntity);
    }

    @Override
    public List<LanguageDto> findAll() {
        List<LanguageEntity> languageEntities = languageRepository.findAllLanguages();
        return languageMapper.toDtoList(languageEntities);
    }

    @Override
    public CountDto getCount() {
        int sportKindsCount = (int) languageRepository.count();
        return new CountDto(sportKindsCount);
    }

    @Override
    public LanguageDto find(Long id) {
        LanguageEntity languageEntity = languageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Language not found!"));
        return languageMapper.toDto(languageEntity);
    }

    @Override
    @Transactional
    public void update(Long id, LanguageUpdateDto languageDto) {
        LanguageEntity languageEntity = languageMapper.toEntity(languageDto);
        int affectedRaws = languageRepository.update(id, languageEntity);
        if (affectedRaws == 0) {
            new NotFoundException("Language not found!");
        }
        languageRepository.update(id, languageEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        int affectedRaws = languageRepository.removeById(id);
        if (affectedRaws == 0) {
            throw new NotFoundException("Language not found!");
        }
    }
}