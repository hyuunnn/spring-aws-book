package com.spring.book.springboot.web.dto;

import com.spring.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
  // Posts 클래스(Entity)와 매우 유사하지만, Dto 클래스를 추가로 생성했다.
  // Entity 클래스는 DB와 밀접한 관계의 핵심 클래스이기 때문에 DB Layer(Entity)와 View Layer(Dto)로 역할을 구분한 것이다.

  private String title;
  private String content;
  private String author;

  @Builder
  public PostsSaveRequestDto(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public Posts toEntity() {
    return Posts.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
  }
}
