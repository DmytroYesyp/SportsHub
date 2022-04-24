package com.sportshub.security.entities.repositories;
import com.sportshub.security.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("LeagueRepository")
public interface LeagueRepository extends JpaRepository<League, Long> {}