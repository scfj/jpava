package com.github.scfj.jpava.specifications;

import com.github.scfj.jpava.specifications.compose.ComposeStrategy;
import com.github.scfj.jpava.specifications.compose.OrComposeStrategy;
import com.github.scfj.jpava.specifications.predicates.IgnoreCasePredicateStrategy;
import com.github.scfj.jpava.specifications.predicates.MatchCasePredicateStrategy;
import com.github.scfj.jpava.specifications.predicates.PredicateStrategy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Full text search specification. Searches given substring in all columns
 *
 * @param <T> - entity class which columns to scan
 */
public class TextSpecification<T> extends LogicalSpecification<T> {
    private final FieldNames fieldNames;
    private final String searchQuery;
    private final ComposeStrategy composeStrategy;
    private final PredicateStrategy predicateStrategy;

    public TextSpecification(String searchQuery,
                             FieldNames fieldNames,
                             ComposeStrategy composeStrategy,
                             PredicateStrategy predicateStrategy) {
        this.fieldNames = fieldNames;
        this.searchQuery = searchQuery;
        this.composeStrategy = composeStrategy;
        this.predicateStrategy = predicateStrategy;
    }

    public TextSpecification(String searchQuery, FieldNames fieldNames, ComposeStrategy composeStrategy) {
        this(searchQuery, fieldNames, composeStrategy, new IgnoreCasePredicateStrategy(searchQuery));
    }

    public TextSpecification(String searchQuery, FieldNames fieldNames) {
        this(searchQuery, fieldNames, new OrComposeStrategy());
    }

    @Override
    public final Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return composeStrategy.composePredicates(builder, predicatesForColumns(root, builder));
    }

    public TextSpecification<T> except(String... fields) {
        return new TextSpecification<>(searchQuery, fieldNames.except(fields), composeStrategy, predicateStrategy);
    }

    public TextSpecification<T> matchCase() {
        return new TextSpecification<>(searchQuery, fieldNames, composeStrategy,
                                       new MatchCasePredicateStrategy(searchQuery));
    }

    private Predicate[] predicatesForColumns(Root<?> root, CriteriaBuilder builder) {
        return fieldNames.stream()
                .map(field -> predicateStrategy.buildPredicate(field, root, builder))
                .toArray(Predicate[]::new);
    }
}
