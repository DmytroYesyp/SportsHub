package com.sportshub.dto.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BooleanPayload {
    @NotNull
    private Boolean value;
}
