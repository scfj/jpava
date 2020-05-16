package com.github.scfj.jpava.specifications.predicates;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Builds predicate for given fieldName in where clause:
 * {@code buildPredicate("age", root, builder)
 * -> where ... AND (age > 5) AND ...}
 */
@FunctionalInterface
public interface PredicateStrategy {
    Predicate buildPredicate(
            String fieldName,
            Root<?> root,
            CriteriaBuilder builder
    );
}
