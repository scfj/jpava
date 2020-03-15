package com.github.scfj.jpava.specifications.predicates;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface PredicateStrategy {
    Predicate buildPredicate(String fieldName, Root<?> root, CriteriaBuilder builder);
}
