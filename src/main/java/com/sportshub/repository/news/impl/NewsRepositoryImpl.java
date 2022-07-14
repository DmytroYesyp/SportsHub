package com.sportshub.repository.news.impl;

import com.sportshub.dto.news.NewsSearchFilters;
import com.sportshub.entity.league.LeagueEntity;
import com.sportshub.entity.news.NewsEntity;
import com.sportshub.repository.news.CustomNewsRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@AllArgsConstructor
public class NewsRepositoryImpl implements CustomNewsRepository {

    private final EntityManager entityManager;

    public List<NewsEntity> findAll(NewsSearchFilters newsSearchFilters, int limit, Integer page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> criteriaQuery = criteriaBuilder.createQuery(NewsEntity.class);
        Root<NewsEntity> queryRoot = criteriaQuery.from(NewsEntity.class);

        Predicate predicate = createPredicate(criteriaBuilder, queryRoot, newsSearchFilters);

        criteriaQuery.where(predicate);

        criteriaQuery.orderBy(criteriaBuilder.asc(queryRoot.get("mainPageOrder")));

        TypedQuery<NewsEntity> query = entityManager.createQuery(criteriaQuery);
        query.setMaxResults(limit);


        if (page != null) {
            query.setFirstResult((page - 1) * limit);
        }

        return query.getResultList();
    }

    public int count(NewsSearchFilters newsSearchFilters) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
        Root<NewsEntity> queryRoot = countCriteria.from(NewsEntity.class);

        Predicate predicate = createPredicate(criteriaBuilder, queryRoot, newsSearchFilters);

        countCriteria.where(predicate);

        TypedQuery<Long> query = entityManager.createQuery(countCriteria.select(criteriaBuilder.count(queryRoot)));

        return (int) (long) query.getSingleResult();
    }

    private Predicate createPredicate(CriteriaBuilder criteriaBuilder, Root<NewsEntity> queryRoot,
                                      NewsSearchFilters newsSearchFilters) {

        List<Predicate> predicates = new ArrayList<>();

        String title = newsSearchFilters.getTitle();
        if (!Strings.isBlank(title)) {
            predicates.add(criteriaBuilder.like(queryRoot.get("title"), '%' + title.replaceAll("%", "") + '%'));
        }

        String description = newsSearchFilters.getDescription();
        if (!Strings.isBlank(description)) {
            predicates.add(criteriaBuilder.like(queryRoot.get("description"), '%' + description.replaceAll("%", "") + '%'));
        }

        Instant publicationDateFrom = newsSearchFilters.getPublicationDateFrom();
        if (publicationDateFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(queryRoot.get("publicationDate"), publicationDateFrom));
        }

        Instant publicationDateTo = newsSearchFilters.getPublicationDateTo();
        if (publicationDateTo != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("publicationDate"), publicationDateTo));
        }

        Boolean isPublished = newsSearchFilters.getIsPublished();
        if (isPublished != null) {
            predicates.add(criteriaBuilder.equal(queryRoot.get("isPublished"), isPublished));
        }

        Set<Long> leagueIds = newsSearchFilters.getLeagueIds();
        if (leagueIds != null && !leagueIds.isEmpty()) {
            Predicate[] leagueIdPredicates = leagueIds.stream().map(leagueId -> criteriaBuilder.equal(queryRoot.get("league"), leagueId))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(leagueIdPredicates));
        }

        Set<Long> sportKindIds = newsSearchFilters.getSportKindIds();
        if (sportKindIds != null && !sportKindIds.isEmpty()) {
            Join<NewsEntity, LeagueEntity> leagueJoin = queryRoot.join("league", JoinType.LEFT);
            Predicate[] sportKindIdPredicates = sportKindIds.stream().map(sportKindId -> criteriaBuilder.equal(leagueJoin.get("sportKind"), sportKindId))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(sportKindIdPredicates));
        }

        Set<Long> teamIds = newsSearchFilters.getTeamIds();
        if (teamIds != null && !teamIds.isEmpty()) {
            Predicate[] teamIdPredicates = teamIds.stream().map(teamId -> criteriaBuilder.isMember(teamId, queryRoot.get("teamIds")))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(teamIdPredicates));
        }


        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
