package com.github.scfj.jpava.specifications.predicates;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
