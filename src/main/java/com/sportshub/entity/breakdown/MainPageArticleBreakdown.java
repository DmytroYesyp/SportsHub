package com.sportshub.entity.breakdown;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "main_page_article_breakdowns")
public class MainPageArticleBreakdown {
    @Id
    private Integer breakdownPosition;
}
