package com.sportshub.dto.dislikes;
import lombok.Data;

@Data
public class DislikesUpdateDto {
    private Long commentId;
    private Long userId;
}