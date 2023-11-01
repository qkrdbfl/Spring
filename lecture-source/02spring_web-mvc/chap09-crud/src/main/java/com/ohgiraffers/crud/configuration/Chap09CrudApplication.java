package com.ohgiraffers.crud.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/* 실행 클래스의 위치가 기본 패키지에서 벗어나게 될 경우 component scan 영역이 달라지므로 명시할 필요가 생김 */
@ComponentScan(basePackages = "com.ohgiraffers.crud")
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class Chap09CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap09CrudApplication.class, args);
    }

}
