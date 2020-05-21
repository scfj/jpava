package com.github.scfj.jpava.predicates;

/**
 * {@code Matchers} instances create {@code Matcher}
 * instances. {@link com.github.scfj.jpava.predicates.Matcher}
 */
@FunctionalInterface
public interface Matchers {
    Matcher forText(String text);
}
