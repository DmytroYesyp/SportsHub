package com.sportshub.dto.likes;
import lombok.Data;

@Data
public class LikesUpdateDto {
    private Long commentId;
    private Long userId;
}