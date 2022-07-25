package com.sportshub.entity.page.content;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "page_content")
public class PageContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 15000)
    private String text;
}
