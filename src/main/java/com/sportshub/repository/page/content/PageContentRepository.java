package com.sportshub.repository.page.content;

import com.sportshub.entity.page.content.PageContentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PageContentRepository extends JpaRepository<PageContentEntity, Long> {

    @Query("FROM PageContentEntity ")
    List<PageContentEntity> findAllPageContent(Pageable pageable);


    @Modifying
    @Query("""
            UPDATE PageContentEntity SET
                text =:#{#entity.text}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") PageContentEntity entity);

    @Modifying
    @Query("DELETE FROM PageContentEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
