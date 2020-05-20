package com.github.scfj.jpava;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class FieldNamesTest {
    @Test
    void streamTest() {
        assertThat(
                new FieldNames(Post.class).stream().collect(toSet()),
                containsInAnyOrder("id", "title", "content")
        );
    }

    @Test
    void exceptTest() {
    }
}