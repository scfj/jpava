package com.github.scfj.jpava.predicates;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * {@code Matcher} instances create expressions for SQL WHERE-clause.
 */
@FunctionalInterface
public interface Matcher {
    Predicate toPredicate(
            String fieldName,
            Root<?> root,
            CriteriaBuilder builder
    );
}
