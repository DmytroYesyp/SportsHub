package com.sportshub.dto.count;

import lombok.Data;

@Data
public class CountLikesDto {
    private final int commId;
    private final int count;
}