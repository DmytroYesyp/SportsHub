package com.sportshub.dto.dislikes;
import lombok.Data;

@Data
public class DislikesDto {
    private Long id;
    private Long commentId;
    private Long userId;
}