package com.github.scfj.jpava;

import com.github.scfj.jpava.compose.ComposeStrategy;
import com.github.scfj.jpava.predicates.Matcher;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TextSpecification<T> extends LogicalSpecification<T> {
    private final FieldNames fieldNames;
    private final ComposeStrategy composeStrategy;
    private final Matcher matcher;

    public TextSpecification(
            FieldNames fieldNames,
            ComposeStrategy composeStrategy,
            Matcher matcher
    ) {
        this.fieldNames = fieldNames;
        this.composeStrategy = composeStrategy;
        this.matcher = matcher;
    }

    @Override
    public final Predicate toPredicate(
            Root<T> root,
            CriteriaQuery<?> query,
            CriteriaBuilder builder
    ) {
        return composeStrategy.composePredicates(
                builder, predicatesForColumns(root, builder)
        );
    }

    private Predicate[] predicatesForColumns(
            Root<?> root, CriteriaBuilder builder
    ) {
        return fieldNames.stream()
                .map(field -> matcher.toPredicate(
                        field, root, builder
                )).toArray(Predicate[]::new);
    }
}
