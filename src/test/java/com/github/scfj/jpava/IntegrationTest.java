package com.github.scfj.jpava;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static com.github.scfj.jpava.TextSpecifications.forClass;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

@DataJpaTest
@ContextConfiguration("application.xml")
@EnableAutoConfiguration
public class IntegrationTest {
    TextSpecifications<Post> posts = forClass(Post.class);
    Post post1 = new Post("This is hello", "world written");
    Post post2 = new Post("in java", "with spring,");
    Post post3 = new Post("spring boot,", "its tests");
    Post post4 = new Post("and its", "data jpa");
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository.saveAll(asList(
                post1, post2, post3, post4
        ));
    }

    @Test
    void searchHello() {
        assertThat(
                postRepository.findAll(posts.withText("Hello")),
                allOf(containsInAnyOrder(post1), not(containsInAnyOrder(post2, post3, post4)))
        );
    }

    @Test
    void searchSpring() {
        assertThat(
                postRepository.findAll(posts.withText("spring")),
                allOf(containsInAnyOrder(post2, post3), not(containsInAnyOrder(post1, post4)))
        );
    }

    @Test
    void searchIts() {
        assertThat(
                postRepository.findAll(posts.withText("its")),
                allOf(containsInAnyOrder(post3, post4), not(containsInAnyOrder(post1, post2)))
        );
    }
}
