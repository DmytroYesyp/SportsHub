package com.sportshub.dto.comment;
import lombok.Data;

import java.time.Instant;

@Data
public class CommentUpdateDto {
    private String text;
    private Long userId;
    private Long newsId;
    private Instant publicationDate;
    private boolean isEdited;
    private Long commId;
}
