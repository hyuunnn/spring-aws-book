package com.spring.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA가 클래스를 상속할 때 createdDate, modifiedDate 필드도 column으로 인식한다.
@EntityListeners(AuditingEntityListener.class) // Auditing 기능을 포함시킨다.
public class BaseTimeEntity {

  @CreatedDate // Entity가 생성되어 저장될 때 시간 자동 저장
  private LocalDateTime createdDate;

  @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간 자동 저장
  private LocalDateTime modifiedDate;

}
