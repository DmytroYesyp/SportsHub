package com.sportshub.dto.sport.kind;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SportKindContentDto {
    @NotBlank
    private String name;

}
