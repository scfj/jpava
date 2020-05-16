package com.github.scfj.jpava;

import org.springframework.data.jpa.domain.Specification;

/**
 * Provides default {@code and} and {@code or} for Specification interface
 *
 * @param <T> - specification is used to find objects
 *            of type {@code T} in repository
 */
public abstract class LogicalSpecification<T> implements Specification<T> {
    @Override
    public final Specification<T> and(Specification<T> other) {
        return (root, query, builder) -> builder.and(
                this.toPredicate(root, query, builder),
                other.toPredicate(root, query, builder)
        );
    }

    @Override
    public final Specification<T> or(Specification<T> other) {
        return (root, query, builder) -> builder.or(
                this.toPredicate(root, query, builder),
                other.toPredicate(root, query, builder)
        );
    }
}
