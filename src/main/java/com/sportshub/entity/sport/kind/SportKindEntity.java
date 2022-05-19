package com.sportshub.entity.sport.kind;
import com.sportshub.entity.league.LeagueEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Data
@Entity
@Table(name = "kinds_of_sport")
public class SportKindEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "sportKind")
    private List<LeagueEntity> leagues;
}
