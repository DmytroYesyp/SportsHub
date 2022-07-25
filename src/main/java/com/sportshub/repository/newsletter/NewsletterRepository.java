package com.sportshub.repository.newsletter;
import com.sportshub.entity.newsletter.NewsletterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface NewsletterRepository extends JpaRepository<NewsletterEntity, Long> {
    @Query("FROM NewsletterEntity WHERE leagueId = :leagueId ORDER BY id")
    List<NewsletterEntity> findAllNewsletters(@Param("leagueId") Long leagueId);

    @Modifying
    @Query("""
            UPDATE NewsletterEntity SET 
                mail = :#{#entity.mail}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") NewsletterEntity entity);

    @Modifying
    @Query("DELETE FROM NewsletterEntity WHERE userId = :id and leagueId = :leagueId")
    int removeById(@Param("id") Long id, @Param("leagueId") Long leagueId);
}