package com.spring.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// MyBatis에서 Dao(Data Access Object)라고 불리는 DB Layer, JPA에서는 Repository라고 부른다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
