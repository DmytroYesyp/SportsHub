package com.sportshub.repository.news;

import com.sportshub.entity.news.NewsEntity;
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
                league.id = :#{#entity.league.id}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") NewsEntity entity);

    @Modifying
    @Query("DELETE FROM NewsEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}