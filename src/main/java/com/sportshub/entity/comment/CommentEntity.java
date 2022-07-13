package com.sportshub.entity.comment;

import com.sportshub.entity.news.NewsEntity;
import com.sportshub.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;


@Data
@Entity
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Long userId;
    private Long newsId;
    private Instant publicationDate;
    private boolean isEdited;
}
