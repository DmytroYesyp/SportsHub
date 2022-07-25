package com.sportshub.dto.newsletter;
import lombok.Data;

@Data
public class NewsletterUpdateDto {
    private Long userId;
    private Long leagueId;
    private String mail;
}