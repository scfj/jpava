package com.github.scfj.jpava.specifications.compose;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class OrComposeStrategy implements ComposeStrategy {
    @Override
    public Predicate composePredicates(CriteriaBuilder builder, Predicate[] predicatesForColumns) {
        return builder.or(predicatesForColumns);
    }
}
