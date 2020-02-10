# jpava
Lets you search text among all fields in a pretty way.

## Installation

Add dependency to your `pom.xml`.
```xml
<dependency>
  <groupId>com.github.scfj</groupId>
  <artifactId>jpava</artifactId>
  <version>1.2.0</version>
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
import static io.github.jjpava.jpava.specifications.TextSpecifications.withText;
```

Now you can perform complex search operation with ease!
```java
class Controller {
    public void findExamples() {
        /*
         * It will find all posts that has substring "hello" in all post's fields (title, preview, content etc)
         */
        postRepository.findAll(withText("hello").inAnyColumnOf(Post.class));
        
        /*
         * Matches only posts that has "hello" in all fields.
         */
        postRepository.findAll(withText("hello").inEveryColumnOf(Post.class));
        
        /*
         * Searches for "hello". "Hello" or "HELLO" won't be found.
         */
        postRepository.findAll(withText("hello").inAnyColumnOf(Post.class).matchCase());
        
        /*
         * Combine specifications.
         */
        postRepository.findAll(
            withText("hello").inAnyColumnOf(Post.class).and(
                withText("world").inAnyColumnOf(Post.class)
            ).or(yourCustomSpecification)
        );
    }
}
```

## Demo
See demo here: [jjpava/jpava-demo](https://github.com/jjpava/jpava-demo).
