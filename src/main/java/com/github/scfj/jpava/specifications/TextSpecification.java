package com.github.scfj.jpava.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;

import static java.util.Arrays.stream;

public abstract class TextSpecification<T> extends LogicalSpecification<T> {
    private String searchQuery;
    private Class<T> type;

    public TextSpecification(String searchQuery, Class<T> type) {
        this.searchQuery = searchQuery;
        this.type = type;
    }

    @Override
    public final Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return buildPredicates(builder, eachColumnHasTextPredicates(root, builder));
    }

    public TextSpecification<T> matchCase() {
        return new TextSpecification<T>(searchQuery, type) {
            @Override
            protected Predicate buildPredicates(CriteriaBuilder builder, Predicate... predicates) {
                return TextSpecification.this.buildPredicates(builder, predicates);
            }

            @Override
            protected Predicate match(String field, Root<?> root, CriteriaBuilder builder) {
                return builder.like(
                        root.get(field).as(String.class),
                        "%" + searchQuery + "%"
                );
            }
        };
    }

    protected Predicate buildPredicates(CriteriaBuilder builder, Predicate... predicates) {
        return builder.or(predicates);
    }

    protected Predicate match(String field, Root<?> root, CriteriaBuilder builder) {
        return builder.like(
                builder.lower(root.get(field).as(String.class)),
                "%" + searchQuery.toLowerCase() + "%"
        );
    }

    protected boolean chooseField(Field field) {
        return true;
    }

    private Predicate[] eachColumnHasTextPredicates(Root<?> root, CriteriaBuilder builder) {
        return stream(type.getDeclaredFields())
                .filter(this::chooseField)
                .map(Field::getName)
                .map(field -> match(field, root, builder))
                .toArray(Predicate[]::new);
    }
}
