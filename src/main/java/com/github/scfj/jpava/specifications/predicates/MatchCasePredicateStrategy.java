package com.github.scfj.jpava.specifications.predicates;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Builds query that searches substring in some column:
 * {@code buildPredicate("content"...) -> where table.content LIKE '%Some Text%'}
 */
public class MatchCasePredicateStrategy implements PredicateStrategy {
    private final String searchQuery;

    public MatchCasePredicateStrategy(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public Predicate buildPredicate(String fieldName, Root<?> root, CriteriaBuilder builder) {
        return builder.like(
                root.get(fieldName).as(String.class),
                "%" + searchQuery + "%"
        );
    }
}
