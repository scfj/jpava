package com.github.scfj.jpava.predicates;

public class CaseMatchers implements Matchers {
    public Matcher forText(String searchQuery) {
        return (fieldName, root, builder) -> builder.like(
                root.get(fieldName).as(String.class),
                "%" + searchQuery + "%"
        );
    }
}
