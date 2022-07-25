package com.sportshub.dto.newsletter;
import lombok.Data;

@Data
public class NewsletterDto {
    private Long id;
    private Long userId;
    private Long leagueId;
    private String mail;
}