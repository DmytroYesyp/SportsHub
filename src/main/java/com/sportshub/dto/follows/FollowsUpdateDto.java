package com.sportshub.dto.follows;
import lombok.Data;

@Data
public class FollowsUpdateDto {
    private Long teamId;
    private Long userId;
}