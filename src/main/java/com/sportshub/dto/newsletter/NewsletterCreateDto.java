package com.sportshub.dto.newsletter;
import lombok.Data;

@Data
public class NewsletterCreateDto {
    private Long userId;
    private Long leagueId;
    private String mail;
}