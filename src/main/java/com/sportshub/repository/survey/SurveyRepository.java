package com.sportshub.repository.survey;

import com.sportshub.entity.survey.SurveyEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {

    @Query("FROM SurveyEntity")
    List<SurveyEntity> findAllSurveys(Pageable pageable);

    @Modifying
    @Query("""
            UPDATE SurveyEntity SET 
                topic = :#{#entity.topic},
                text = :#{#entity.text}
            WHERE id = :id
            """)
    int update(@Param("id") Long id, @Param("entity") SurveyEntity entity);

    @Modifying
    @Query("DELETE FROM SurveyEntity WHERE id = :id")
    int removeById(@Param("id") Long id);
}
