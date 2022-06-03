package com.sportshub.dto.language;
import lombok.Data;

@Data
public class LanguageUpdateDto {
    private String name;
    private String locale;
    private boolean hidden;
}