package com.github.scfj.jpava.predicates;

/**
 * Instances of this class builds objects which create
 * SQL-expressions for WHERE-clause such as
 * {@code LOWER(some_column) LIKE LOWER(%string_query%)}
 */
public class IgnoreCaseMatchers implements Matchers {
    @Override
    public Matcher forText(String searchQuery) {
        return (fieldName, root, builder) -> builder.like(
                builder.lower(root.get(fieldName).as(String.class)),
                "%" + searchQuery.toLowerCase() + "%"
        );
    }
}
