package com.sportshub.dto.follows;
import lombok.Data;

@Data
public class FollowsCreateDto {
    private Long teamId;
    private Long userId;
}