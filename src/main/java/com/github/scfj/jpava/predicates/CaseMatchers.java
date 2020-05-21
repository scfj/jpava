package com.github.scfj.jpava.predicates;

/**
 * Instances of this class builds objects which create
 * SQL-expressions for WHERE-clause such as
 * {@code some_column LIKE %string_query%}
 */
public class CaseMatchers implements Matchers {
    public Matcher forText(String searchQuery) {
        return (fieldName, root, builder) -> builder.like(
                root.get(fieldName).as(String.class),
                "%" + searchQuery + "%"
        );
    }
}
