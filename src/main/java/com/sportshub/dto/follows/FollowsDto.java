package com.sportshub.dto.follows;
import lombok.Data;

@Data
public class FollowsDto {
    private Long id;
    private Long teamId;
    private Long userId;
}