package com.github.scfj.jpava;

import com.github.scfj.jpava.compose.AndCompose;
import com.github.scfj.jpava.compose.ComposeStrategy;
import com.github.scfj.jpava.compose.OrCompose;
import com.github.scfj.jpava.predicates.CaseMatchers;
import com.github.scfj.jpava.predicates.IgnoreCaseMatchers;
import com.github.scfj.jpava.predicates.Matchers;

/**
 * Provides simple DSL for building {@code Specification}
 * {@link org.springframework.data.jpa.domain.Specification}
 *
 * @param <T> entity type to be searched
 *            (annotated with {@code @javax.persistence.Entity})
 */
public class TextSpecifications<T> {
    private final ComposeStrategy composeStrategy;
    private FieldNames fieldNames;
    private Matchers matchers = new IgnoreCaseMatchers();

    private TextSpecifications(FieldNames fieldNames) {
        this(fieldNames, new OrCompose());
    }

    private TextSpecifications(
            FieldNames fieldNames,
            ComposeStrategy composeStrategy
    ) {
        this.fieldNames = fieldNames;
        this.composeStrategy = composeStrategy;
    }

    public static <T> TextSpecifications<T> forClass(
            Class<T> entityClass
    ) {
        return inAnyColumnOf(entityClass);
    }

    public static <T> TextSpecifications<T> inAnyColumnOf(
            Class<T> entityClass
    ) {
        return new TextSpecifications<>(new FieldNames(entityClass));
    }

    public static <T> TextSpecifications<T> inAllColumnsOf(
            Class<T> entityClass
    ) {
        return new TextSpecifications<T>(
                new FieldNames(entityClass), new AndCompose()
        );
    }

    public TextSpecifications<T> except(String... fields) {
        fieldNames = fieldNames.except(fields);
        return this;
    }

    public TextSpecifications<T> matchCase() {
        matchers = new CaseMatchers();
        return this;
    }

    public TextSpecifications<T> ignoreCase() {
        matchers = new IgnoreCaseMatchers();
        return this;
    }

    public TextSpecification<T> withText(String query) {
        return new TextSpecification<T>(
                fieldNames, composeStrategy, matchers.forText(query)
        );
    }
}
