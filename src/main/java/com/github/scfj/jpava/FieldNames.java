package com.github.scfj.jpava;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class FieldNames {
    private final Set<String> fieldNames;

    public FieldNames(Class<?> aClass) {
        this(Arrays.stream(aClass.getDeclaredFields())
                .map(Field::getName)
                .collect(toSet()));
    }

    private FieldNames(Set<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public Stream<String> stream() {
        return fieldNames.stream();
    }

    public FieldNames except(String... excludedFields) {
        return except(Arrays.stream(excludedFields).collect(toSet()));
    }

    private FieldNames except(Collection<String> excludedFields) {
        return new FieldNames(this.fieldNames.stream()
                .filter(f -> !excludedFields.contains(f))
                .collect(toSet())
        );
    }
}
