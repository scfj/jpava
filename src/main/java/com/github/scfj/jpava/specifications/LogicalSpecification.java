package com.github.scfj.jpava.specifications;

import org.springframework.data.jpa.domain.Specification;

public abstract class LogicalSpecification<T> implements Specification<T> {
    @Override
    public final Specification<T> and(Specification<T> other) {
        return (Specification<T>) (root, query, builder) -> builder.and(
                LogicalSpecification.this.toPredicate(root, query, builder),
                other.toPredicate(root, query, builder)
        );
    }

    @Override
    public final Specification<T> or(Specification<T> other) {
        return (Specification<T>) (root, query, builder) -> builder.or(
                LogicalSpecification.this.toPredicate(root, query, builder),
                other.toPredicate(root, query, builder)
        );
    }
}
