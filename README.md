# jpava
[![Maintainability](https://api.codeclimate.com/v1/badges/092631a0e3cbfaf69b1a/maintainability)](https://codeclimate.com/github/scfj/jpava/maintainability)

Lets you search text among all fields pretty.

## Installation

Add dependency to your `pom.xml`.
```xml
<dependency>
  <groupId>com.github.scfj</groupId>
  <artifactId>jpava</artifactId>
  <version>2.0.0</version>
</dependency>
```

## Usage

Add a method(s) accepting specification object to your repository inherited from JpaRepository.

```java
@Repository
interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAll(Specification<Post> spec);
    // OR
    Page<Post> findAll(Specification<Post> spec, Pageable pageable);
}
```

Import single method into your controller/service which uses repository.

```java
import static com.github.scfj.jpava.TextSpecifications.forClass;
// OR
import static com.github.scfj.jpava.TextSpecifications.inAnyColumnOf;
```

Now you can perform complex search operation with ease!
```java
@Controller
class PostController {
    /*
     * Configuration is very simple
     */
    Specification<Post> posts =
        forClass(Post.class) // can be forClass, inAnyColumnOf (= forClass) or inAllColumnsOf
            .except("id", "createdAt") // skip these fields when searching database
            .ignoreCase(); // can be ignoreCase (default) or matchCase

    public List<Post> findExamples() {
        /*
         * It will find all posts that has substring "hello"
         * in any of post's fields (title, preview, content etc)
         */
        return postRepository.findAll(posts.withText("hello"));
    }
}
```

## Deploy

# TODO

## Demo
See demo here (version <= 1.3): [scfj/jpava-demo](https://github.com/scfj/jpava-demo).
