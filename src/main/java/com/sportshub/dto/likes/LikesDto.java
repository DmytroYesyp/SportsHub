package com.sportshub.dto.likes;
import lombok.Data;

@Data
public class LikesDto {
    private Long id;
    private Long commentId;
    private Long userId;
}