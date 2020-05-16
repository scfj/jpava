package com.github.scfj.jpava;

import com.github.scfj.jpava.compose.AndCompose;

/**
 * Provides DSL to build TextSpecification
 *
 * @see TextSpecification
 */
public class TextSpecifications {
    private final String searchQuery;

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
                new AndCompose()
        );
    }
}
