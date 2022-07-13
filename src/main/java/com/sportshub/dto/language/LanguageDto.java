package com.sportshub.dto.language;
import lombok.Data;

@Data
public class LanguageDto {
    private Long id;
    private String name;
    private String locale;
    private boolean hidden;
}
