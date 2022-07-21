package com.sportshub.repository.language;
import com.sportshub.entity.language.LanguageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {
    @Query("FROM LanguageEntity ORDER BY id")
    List<LanguageEntity> findAllLanguages();

    @Modifying
    @Query("""
            UPDATE LanguageEntity SET
                hidden = :#{#entity.hidden}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") LanguageEntity entity);

    @Modifying
    @Query("DELETE FROM LanguageEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
