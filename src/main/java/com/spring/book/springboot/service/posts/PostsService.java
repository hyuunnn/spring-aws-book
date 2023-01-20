package com.spring.book.springboot.service.posts;

import com.spring.book.springboot.domain.posts.Posts;
import com.spring.book.springboot.domain.posts.PostsRepository;
import com.spring.book.springboot.web.dto.PostsResponseDto;
import com.spring.book.springboot.web.dto.PostsSaveRequestDto;
import com.spring.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity())
        .getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
    posts.update(requestDto.getTitle(),
        requestDto.getContent()); // 객체의 값만 변경하면 트랜잭션이 끝나는 시점에 자동으로 변경분을 반영한다. (더티 체킹)
    return id;
  }

  public PostsResponseDto findById(Long id) {
    Posts entity = postsRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
    return new PostsResponseDto(entity);
  }
}
