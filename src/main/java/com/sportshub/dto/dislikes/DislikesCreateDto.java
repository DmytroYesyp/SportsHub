package com.sportshub.dto.dislikes;
import lombok.Data;

@Data
public class DislikesCreateDto {
    private Long commentId;
    private Long userId;
}