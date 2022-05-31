package com.sportshub.repository.news.impl;

import com.sportshub.dto.news.NewsSearchFilters;
import com.sportshub.entity.news.NewsEntity;
import com.sportshub.repository.news.CustomNewsRepository;
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
public class NewsRepositoryImpl implements CustomNewsRepository {

    private final EntityManager entityManager;

    public List<NewsEntity> findAll(NewsSearchFilters newsSearchFilters, int limit, Integer page) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> criteriaQuery = criteriaBuilder.createQuery(NewsEntity.class);
        Root<NewsEntity> queryRoot = criteriaQuery.from(NewsEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        String title = newsSearchFilters.getTitle();
        if (!Strings.isBlank(title)) {
            predicates.add(criteriaBuilder.like(queryRoot.get("title"), '%' + title.replaceAll("%", "") + '%'));
        }

        Instant publicationDateFrom = newsSearchFilters.getPublicationDateFrom();
        if (publicationDateFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(queryRoot.get("publicationDate"), publicationDateFrom));
        }

        Instant publicationDateTo = newsSearchFilters.getPublicationDateTo();
        if (publicationDateTo != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("publicationDate"), publicationDateTo));
        }

        Set<Long> leagueIds = newsSearchFilters.getLeagueIds();
        if (leagueIds != null && !leagueIds.isEmpty()) {
            Predicate[] leagueIdPredicates = leagueIds.stream().map(leagueId -> criteriaBuilder.equal(queryRoot.get("league"), leagueId))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(leagueIdPredicates));
        }

        Set<Long> kindsOfSportIds = newsSearchFilters.getKindsOfSportIds();
        if (kindsOfSportIds != null && !kindsOfSportIds.isEmpty()) {
            Predicate[] kindsOfSportIdPredicates = kindsOfSportIds.stream().map(kindsOfSportId -> criteriaBuilder.isMember(kindsOfSportId, queryRoot.get("kindsOfSportIds")))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(kindsOfSportIdPredicates));
        }

        Set<Long> teamIds = newsSearchFilters.getTeamIds();
        if (teamIds != null && !teamIds.isEmpty()) {
            Predicate[] teamIdPredicates = teamIds.stream().map(teamId -> criteriaBuilder.isMember(teamId, queryRoot.get("teamIds")))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(teamIdPredicates));
        }


        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));

        criteriaQuery.where(finalPredicate);

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

        List<Predicate> predicates = new ArrayList<>();

        String title = newsSearchFilters.getTitle();
        if (!Strings.isBlank(title)) {
            predicates.add(criteriaBuilder.like(queryRoot.get("title"), '%' + title.replaceAll("%", "") + '%'));
        }

        Instant publicationDateFrom = newsSearchFilters.getPublicationDateFrom();
        if (publicationDateFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(queryRoot.get("publicationDate"), publicationDateFrom));
        }

        Instant publicationDateTo = newsSearchFilters.getPublicationDateTo();
        if (publicationDateTo != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(queryRoot.get("publicationDate"), publicationDateTo));
        }

        Set<Long> leagueIds = newsSearchFilters.getLeagueIds();
        if (leagueIds != null && !leagueIds.isEmpty()) {
            Predicate[] leagueIdPredicates = leagueIds.stream().map(leagueId -> criteriaBuilder.equal(queryRoot.get("league"), leagueId))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(leagueIdPredicates));
        }

        Set<Long> kindsOfSportIds = newsSearchFilters.getKindsOfSportIds();
        if (kindsOfSportIds != null && !kindsOfSportIds.isEmpty()) {
            Predicate[] kindsOfSportIdPredicates = kindsOfSportIds.stream().map(kindsOfSportId -> criteriaBuilder.isMember(kindsOfSportId, queryRoot.get("kindsOfSportIds")))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(kindsOfSportIdPredicates));
        }

        Set<Long> teamIds = newsSearchFilters.getTeamIds();
        if (teamIds != null && !teamIds.isEmpty()) {
            Predicate[] teamIdPredicates = teamIds.stream().map(teamId -> criteriaBuilder.equal(queryRoot.get("teamIds"), teamId))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(teamIdPredicates));
        }


        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(Predicate[]::new));

        countCriteria.where(finalPredicate);

        TypedQuery<Long> query = entityManager.createQuery(countCriteria.select(criteriaBuilder.count(queryRoot)));

        return (int) (long) query.getSingleResult();
    }
}
