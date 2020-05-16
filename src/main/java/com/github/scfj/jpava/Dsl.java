package com.github.scfj.jpava;

import com.github.scfj.jpava.compose.AndCompose;
import com.github.scfj.jpava.compose.ComposeStrategy;
import com.github.scfj.jpava.compose.OrCompose;
import com.github.scfj.jpava.predicates.CaseMatchers;
import com.github.scfj.jpava.predicates.IgnoreCaseMatchers;
import com.github.scfj.jpava.predicates.Matchers;

public class Dsl<T> {
    private final ComposeStrategy composeStrategy;
    private FieldNames fieldNames;
    private Matchers matchers = new IgnoreCaseMatchers();

    private Dsl(FieldNames fieldNames) {
        this(fieldNames, new OrCompose());
    }

    private Dsl(FieldNames fieldNames, ComposeStrategy composeStrategy) {
        this.fieldNames = fieldNames;
        this.composeStrategy = composeStrategy;
    }

    public static <T> Dsl<T> forClass(Class<T> entityClass) {
        return inAnyColumnOf(entityClass);
    }

    public static <T> Dsl<T> inAnyColumnOf(Class<T> entityClass) {
        return new Dsl<>(new FieldNames(entityClass));
    }

    public static <T> Dsl<T> inAllColumnsOf(Class<T> entityClass) {
        return new Dsl<T>(new FieldNames(entityClass), new AndCompose());
    }

    public Dsl<T> except(String... fields) {
        fieldNames = fieldNames.except(fields);
        return this;
    }

    public Dsl<T> matchCase() {
        matchers = new CaseMatchers();
        return this;
    }

    public Dsl<T> ignoreCase() {
        matchers = new IgnoreCaseMatchers();
        return this;
    }

    public TextSpecification<T> withText(String query) {
        return new TextSpecification<T>(
                fieldNames, composeStrategy, matchers.forText(query)
        );
    }
}
