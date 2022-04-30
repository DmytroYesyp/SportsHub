package com.sportshub.security.entities.repositories;
import com.sportshub.security.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("NewsRepository")
public interface NewsRepository extends JpaRepository<News, Long> {}