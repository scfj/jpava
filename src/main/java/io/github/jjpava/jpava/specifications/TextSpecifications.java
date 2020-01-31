package io.github.jjpava.jpava.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public class TextSpecifications {
    private String searchQuery;

    private TextSpecifications(String text) {
        this.searchQuery = "%" + text + "%";
    }

    public static TextSpecifications withText(String text) {
        return new TextSpecifications(text);
    }

    public <T> TextSpecification<T> inAnyColumnOf(Class<T> aClass) {
        return new TextSpecification<T>(searchQuery, aClass) {
        };
    }

    public <T> TextSpecification<T> inEveryColumnOf(Class<T> aClass) {
        return new TextSpecification<T>(searchQuery, aClass) {
            @Override
            protected Predicate buildPredicates(CriteriaBuilder builder, Predicate... predicates) {
                return builder.and(predicates);
            }
        };
    }
}

