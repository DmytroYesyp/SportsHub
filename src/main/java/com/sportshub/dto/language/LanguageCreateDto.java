package com.sportshub.dto.language;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class LanguageCreateDto {
    @NotBlank
    private String name;
    private String locale;
    private boolean hidden;
}