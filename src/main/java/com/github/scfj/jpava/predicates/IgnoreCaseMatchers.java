package com.github.scfj.jpava.predicates;

public class IgnoreCaseMatchers implements Matchers {
    @Override
    public Matcher forText(String searchQuery) {
        return (fieldName, root, builder) -> builder.like(
                builder.lower(root.get(fieldName).as(String.class)),
                "%" + searchQuery.toLowerCase() + "%"
        );
    }
}
