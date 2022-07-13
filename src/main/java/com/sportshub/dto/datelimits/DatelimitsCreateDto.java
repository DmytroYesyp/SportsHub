package com.sportshub.dto.datelimits;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class DatelimitsCreateDto {
    @NotBlank
    private String name;
    private Long datelim;

}