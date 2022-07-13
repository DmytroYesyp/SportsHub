package com.sportshub.dto.likes;
import lombok.Data;

@Data
public class LikesCreateDto {
    private Long commentId;
    private Long userId;
}