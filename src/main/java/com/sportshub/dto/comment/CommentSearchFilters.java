package com.sportshub.dto.comment;

import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class CommentSearchFilters {
    private String text;
    private Long userId;
    private Long newsId;
    private Instant publicationDate;
    private boolean isEdited;
    private Long commId;
}
