package com.sportshub.repository.comment.impl;

import com.sportshub.dto.comment.CommentSearchFilters;
import com.sportshub.entity.comment.CommentEntity;
import com.sportshub.repository.comment.CustomCommentRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
@AllArgsConstructor
public class CommentRepositoryImpl implements CustomCommentRepository {

    private final EntityManager entityManager;

    public List<CommentEntity> findAll(CommentSearchFilters commentSearchFilters, int limit, Integer page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommentEntity> criteriaQuery = criteriaBuilder.createQuery(CommentEntity.class);
        Root<CommentEntity> queryRoot = criteriaQuery.from(CommentEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        String text = commentSearchFilters.getText();
        if (!Strings.isBlank(text)) {
            predicates.add(criteriaBuilder.like(queryRoot.get("text"), '%' + text.replaceAll("%", "") + '%'));
        }

//        Set<Long> userIds = commentSearchFilters.getUserIds();
//        if (userIds != null && !userIds.isEmpty()) {
//            Predicate[] userIdPredicates = userIds.stream().map(userId -> criteriaBuilder.equal(queryRoot.get("user"), userId))
//                    .toArray(Predicate[]::new);
//            predicates.add(criteriaBuilder.or(userIdPredicates));
//        }

        Long userId = commentSearchFilters.getUserId();
        if (userId != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("userId"), userId));
        }

        Long newsId = commentSearchFilters.getNewsId();
        if (newsId != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("newsId"), newsId));
        }

        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));

        criteriaQuery.where(finalPredicate);

        TypedQuery<CommentEntity> query = entityManager.createQuery(criteriaQuery);
        query.setMaxResults(limit);

        if (page != null) {
            query.setFirstResult((page - 1) * limit);
        }

        return query.getResultList();
    }

    public int count(CommentSearchFilters commentSearchFilters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
        Root<CommentEntity> queryRoot = countCriteria.from(CommentEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        String text = commentSearchFilters.getText();
        if (!Strings.isBlank(text)) {
            predicates.add(criteriaBuilder.like(queryRoot.get("text"), '%' + text.replaceAll("%", "") + '%'));
        }

        Long userId = commentSearchFilters.getUserId();
        if (userId != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("userId"), userId));
        }

        Long newsId = commentSearchFilters.getNewsId();
        if (newsId != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("newsId"), newsId));
        }


        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));

        countCriteria.where(finalPredicate);

        TypedQuery<Long> query = entityManager.createQuery(countCriteria.select(criteriaBuilder.count(queryRoot)));

        return (int) (long) query.getSingleResult();
    }
}
