package com.sportshub.dto.survey;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class SurveyCreateDto {
    private String topic;
    private String text;
}
