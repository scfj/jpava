package com.github.scfj.jpava.specifications;

import com.github.scfj.jpava.specifications.compose.AndComposeStrategy;

public class TextSpecifications {
    private String searchQuery;

    private TextSpecifications(String text) {
        this.searchQuery = text;
    }

    public static TextSpecifications withText(String text) {
        return new TextSpecifications(text);
    }

    public <T> TextSpecification<T> inAnyColumnOf(Class<T> aClass) {
        return new TextSpecification<>(
                searchQuery,
                new FieldNames(aClass)
        );
    }

    public <T> TextSpecification<T> inAllColumnsOf(Class<T> aClass) {
        return new TextSpecification<>(
                searchQuery,
                new FieldNames(aClass),
                new AndComposeStrategy()
        );
    }
}
