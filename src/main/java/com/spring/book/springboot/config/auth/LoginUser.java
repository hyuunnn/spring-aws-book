package com.spring.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성되는 위치
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
