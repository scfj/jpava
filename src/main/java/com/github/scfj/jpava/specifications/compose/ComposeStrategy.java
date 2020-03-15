package com.github.scfj.jpava.specifications.compose;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public interface ComposeStrategy {
    Predicate composePredicates(CriteriaBuilder builder, Predicate[] predicatesForColumns);
}
