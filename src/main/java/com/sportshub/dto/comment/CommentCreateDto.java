package com.sportshub.dto.comment;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
public class CommentCreateDto {
    private String text;
}
