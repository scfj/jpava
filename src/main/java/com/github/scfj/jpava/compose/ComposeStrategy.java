package com.github.scfj.jpava.compose;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/**
 * Composes array of predicates using CriteriaBuilder
 *
 * @see javax.persistence.criteria.Predicate
 * @see javax.persistence.criteria.CriteriaBuilder
 */
@FunctionalInterface
public interface ComposeStrategy {
    Predicate composePredicates(
            CriteriaBuilder builder,
            Predicate[] predicatesForColumns
    );
}
