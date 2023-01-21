package com.spring.book.springboot.domain.posts;

import com.spring.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@NoArgsConstructor // 기본 생성자 추가
@Entity // 테이블과 링크될 클래스, CamelCase 형태의 클래스명을 snake_case 형태로 변환하여 테이블 이름을 매칭한다.
public class Posts extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 설정: auto_increment 활성화
  private Long id;

  @Column(length = 500, nullable = false)
  // Column 어노테이션을 사용하지 않아도 필드명으로 Column 설정을 한다. (변경이 필요한 옵션이 있을 때 사용)
  private String title;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String content;

  private String author; // 기본으로 설정된 varchar(255) 사용

  @Builder // 빌더 패턴으로 사용 가능
  public Posts(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public void update(String title, String content){
    this.title = title;
    this.content = content;
  }
}
