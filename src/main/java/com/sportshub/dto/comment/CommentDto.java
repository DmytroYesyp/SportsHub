package com.sportshub.dto.comment;
import lombok.Data;

import java.time.Instant;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private Long userId;
    private Long newsId;
    private Instant publicationDate;
    private boolean isEdited;
}
