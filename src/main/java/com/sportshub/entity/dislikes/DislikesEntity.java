package com.sportshub.entity.dislikes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dislikes")
public class DislikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long commentId;
    private Long userId;
}