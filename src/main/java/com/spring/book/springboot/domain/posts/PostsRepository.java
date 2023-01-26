package com.spring.book.springboot.domain.posts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// MyBatis에서 Dao(Data Access Object)라고 불리는 DB Layer, JPA에서는 Repository라고 부른다.
// Entity 클래스, 기본 Entity Repository는 함께 위치해야 한다. (Entity 클래스는 Repository에 구현된 기능을 통해 역할을 수행하기 때문이다.) - 매우 밀접한 관계
// 그렇기 때문에 domain 패키지에서 함께 관리한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

  @Query("SELECT p FROM Posts p ORDER BY p.id DESC") // Querydsl
  List<Posts> findAllDesc();
}
