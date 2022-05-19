package com.sportshub.entity.league;

import com.sportshub.entity.sport.kind.SportKindEntity;
import com.sportshub.entity.team.TeamEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Data
@Entity
@Table(name = "league")
public class LeagueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="kinds_of_sport_id")
    private SportKindEntity sportKind;

    @OneToMany(mappedBy = "league")
    private List<TeamEntity> teams;

}