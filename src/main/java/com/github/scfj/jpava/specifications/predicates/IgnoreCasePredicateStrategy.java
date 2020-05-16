package com.github.scfj.jpava.specifications.predicates;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Builds query that searches substring in some column and ignores case:
 * {@code buildPredicate("content"...)
 * -> where LOWER(table.content) LIKE '%some text%'}
 * Given text will be lower cased
 */
public class IgnoreCasePredicateStrategy implements PredicateStrategy {
    private final String searchQuery;

    public IgnoreCasePredicateStrategy(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public Predicate buildPredicate(
            String fieldName, Root<?> root, CriteriaBuilder builder
    ) {
        return builder.like(
                builder.lower(root.get(fieldName).as(String.class)),
                "%" + searchQuery.toLowerCase() + "%"
        );
    }
}
