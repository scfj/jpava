package com.github.scfj.jpava;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.not;

class FieldNamesTest {
    private FieldNames fieldNames = new FieldNames(Post.class);

    @Test
    void streamTest() {
        assertThat(
                fieldNames.stream().collect(toSet()),
                containsInAnyOrder("id", "title", "content")
        );
    }

    @Test
    void exceptTest() {
        assertThat(
                fieldNames.except("id").stream().collect(toSet()),
                both(containsInAnyOrder("title", "content"))
                    .and(not(containsInAnyOrder("id")))
        );
    }
}
