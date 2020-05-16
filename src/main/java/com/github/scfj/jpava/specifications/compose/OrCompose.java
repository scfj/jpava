package com.github.scfj.jpava.specifications.compose;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/**
 * Resulting predicate is evaluated to {@code true} when
 * any of given predicates is evaluated to {@code true}
 */
public class OrCompose implements ComposeStrategy {
    @Override
    public Predicate composePredicates(CriteriaBuilder builder, Predicate[] predicatesForColumns) {
        return builder.or(predicatesForColumns);
    }
}
