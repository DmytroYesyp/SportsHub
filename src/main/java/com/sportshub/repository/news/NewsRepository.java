package com.sportshub.repository.news;

import com.sportshub.entity.news.NewsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends CustomNewsRepository, JpaRepository<NewsEntity, Long> {
    @Modifying
    @Query("""
            UPDATE NewsEntity SET
                title = :#{#entity.title},
                description = :#{#entity.description},
                publicationDate = :#{#entity.publicationDate},
                image = :#{#entity.image},
                league.id = :#{#entity.league.id},
                views = :#{#entity.views}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") NewsEntity entity);

    @Modifying
    @Query("DELETE FROM NewsEntity WHERE id = :id")
    int removeById(@Param("id") Long id);

    @Query(value = "select * from news where publication_date >= current_date - :limitDate order by views desc nulls last", nativeQuery = true)
    List<NewsEntity> findPopular(@Param("limitDate") int limitDate, Pageable pageable);

    @Query(value = "select news.id, news.title, news.description,\n" +
            "news.publication_date, news.image, news.league_id, news.text, news.views from\n" +
            "(select * from news \n" +
            "inner join (select news_id, count (*) as cnt from comments\n" +
            "group by news_id order by cnt desc)\n" +
            "as count on news.id = count.news_id order by cnt desc) as news where publication_date >= current_date - :limitDate",
            nativeQuery = true)
    List<NewsEntity> findCommented(@Param("limitDate") int limitDate, Pageable pageable);
}