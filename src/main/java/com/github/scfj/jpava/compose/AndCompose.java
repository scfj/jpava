package com.github.scfj.jpava.compose;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

/**
 * Resulting predicate is evaluated to {@code true} only and only when
 * all of the given predicates are evaluated to {@code true}
 */
public class AndCompose implements ComposeStrategy {
    @Override
    public Predicate composePredicates(
            CriteriaBuilder builder, Predicate[] predicatesForColumns
    ) {
        return builder.and(predicatesForColumns);
    }
}
