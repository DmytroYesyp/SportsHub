package com.sportshub.dto.sport.kind;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class SportKindCreateDto {
    @NotBlank
    private String name;
}
