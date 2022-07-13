package com.sportshub.dto.comment;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
public class CommentCreateDto {
    private String text;
    private Long userId;
    private Long newsId;
    private Instant publicationDate;
    private boolean isEdited;
}
