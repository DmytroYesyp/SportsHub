package com.sportshub.dto.page.content;

import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class PageContentCreateDto {
    @NotBlank
    private Long id;
    private String text;
}
