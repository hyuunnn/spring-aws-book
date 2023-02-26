package com.spring.book.springboot.config.auth.dto;

import com.spring.book.springboot.domain.user.User;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
  private String name;
  private String email;
  private String picture;

  public SessionUser(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.picture = user.getPicture();
  }
}
