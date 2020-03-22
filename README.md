# jpava
[![Maintainability](https://api.codeclimate.com/v1/badges/092631a0e3cbfaf69b1a/maintainability)](https://codeclimate.com/github/scfj/jpava/maintainability)

Lets you search text among all fields in a pretty way.

## Installation

Add dependency to your `pom.xml`.
```xml
<dependency>
  <groupId>com.github.scfj</groupId>
  <artifactId>jpava</artifactId>
  <version>1.3.0</version>
</dependency>
```

## Usage

Add a method(s), that accepts specification object, to your repository inherited from JpaRepository.

```java
interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll(Specification<Post> spec);
    Page<Post> findAll(Specification<Post> spec, Pageable pageable);
}
```

Import single method into your controller/service that uses repository.

```java
import static com.github.scfj.jpava.specifications.TextSpecifications.withText;
```

Now you can perform complex search operation with ease!
```java
class Controller {
    public void findExamples() {
        List<Post> posts;
        /*
         * It will find all posts that has substring "hello" in all post's fields (title, preview, content etc)
         */
        posts = postRepository.findAll(withText("hello").inAnyColumnOf(Post.class));
        
        /*
         * Matches only posts that has "hello" in all fields.
         */
        posts = postRepository.findAll(withText("hello").inEveryColumnOf(Post.class));
        
        /*
         * Searches for "hello". "Hello" or "HELLO" won't be found.
         */
        posts = postRepository.findAll(withText("hello").inAnyColumnOf(Post.class).matchCase());
        
        /*
         * Combine specifications.
         */
        posts = postRepository.findAll(
            withText("hello").inAnyColumnOf(Post.class).and(
                withText("world").inAnyColumnOf(Post.class)
            ).or(yourCustomSpecification)
        );

        /*
         * You can skip some attributes.
         */
        posts = postRepository.findAll(withText("Hello").inAnyColumnOf(Post.class).except("id"));
    }
}
```

## Demo
See demo here: [scfj/jpava-demo](https://github.com/scfj/jpava-demo).
