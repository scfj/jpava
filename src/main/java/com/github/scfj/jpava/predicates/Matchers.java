package com.github.scfj.jpava.predicates;

@FunctionalInterface
public interface Matchers {
    Matcher forText(String text);
}
