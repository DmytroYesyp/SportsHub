package com.sportshub.repository.page.content;

import com.sportshub.entity.page.content.PageContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PageContentRepository extends JpaRepository<PageContentEntity, Long> {
    @Modifying
    @Query("""
            UPDATE PageContentEntity SET
                title = :#{#entity.title},
                headline =:#{#entity.headline},
                text =:#{#entity.text},
                address =:#{#entity.address},
                tel =:#{#entity.tel},
                email =:#{#entity.email}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") PageContentEntity entity);
}
