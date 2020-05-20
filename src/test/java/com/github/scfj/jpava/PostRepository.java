package com.github.scfj.jpava;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAll(Specification<Post> specification);
}
