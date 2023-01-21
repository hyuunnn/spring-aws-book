package com.spring.book.springboot.service.posts;

import com.spring.book.springboot.domain.posts.Posts;
import com.spring.book.springboot.domain.posts.PostsRepository;
import com.spring.book.springboot.web.dto.PostsResponseDto;
import com.spring.book.springboot.web.dto.PostsSaveRequestDto;
import com.spring.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 트랜잭션, 도메인 간 순서 보장의 역할만 한다. (Controller와 Dao(Repository)의 중간 역할)
// PostsService 클래스를 보면 save, update, findById 메서드들이 PostsRepository (Spring Data JPA)에 구현된 메서드를 사용하여 결과를 만들어낸다.
// PostsApiController 클래스는 PostsService 클래스에 구현된 메서드를 사용한다.
// Controller -> Service -> Repository (이 순서만 봐도 Service는 중간 역할임을 알 수 있다.)
// 그렇기 때문에 핵심, 비지니스 로직은 domain에서 담당해야 한다.
@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity())
        .getId();
  }

  // JPA의 영속성 컨텍스트 때문에 별도의 update 쿼리 없이 DB 데이터를 수정할 수 있다. (PostsService의 save, findById는 Spring Data JPA에 구현된 메서드를 호출하는데, update는 단순히 객체의 필드 값을 변경한다.)
  // JPA의 EntityManager가 활성화된 상태로 트랜잭션 안에서 DB 데이터를 가져오면 영속성 컨텍스트가 유지된 상태이다. (Spring Data JPA는 기본 옵션으로 활성화)
  // 그래서 객체의 값만 변경하면 트랜잭션이 끝나는 시점에 자동으로 변경분을 반영한다. (더티 체킹)
  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
    posts.update(requestDto.getTitle(),
        requestDto.getContent());
    return id;
  }

  public PostsResponseDto findById(Long id) {
    Posts entity = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
    return new PostsResponseDto(entity);
  }
}
