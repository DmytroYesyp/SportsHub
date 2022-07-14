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
                text=:#{#entity.text},
                publicationDate = :#{#entity.publicationDate},
                alternativeText =:#{#entity.alternativeText},
                caption =:#{#entity.caption},
                image = :#{#entity.image},
                isPublished = :#{#entity.isPublished},
                mainPageOrder = :#{#entity.mainPageOrder},
                league.id = :#{#entity.league.id}
            WHERE id = :id 
            """)
    int update(@Param("id") Long id, @Param("entity") NewsEntity entity);

    @Modifying
    @Query("UPDATE NewsEntity SET isPublished = :isPublished WHERE id = :id")
    int updatePublicationStatus(@Param("id") Long id, @Param("isPublished") boolean isPublished);

    @Modifying
    @Query("UPDATE NewsEntity SET mainPageOrder = :mainPageOrder WHERE id = :id")
    int updateMainPageOrder(@Param("id") Long id, @Param("mainPageOrder") Integer mainPageOrder);

    @Modifying
    @Query("DELETE FROM NewsEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}