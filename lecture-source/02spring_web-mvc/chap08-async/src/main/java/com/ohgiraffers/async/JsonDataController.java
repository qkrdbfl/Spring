package com.ohgiraffers.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class JsonDataController {

    private final List<UserDTO> userList;

    public JsonDataController() {
        userList = new ArrayList<>();
        userList.add(new UserDTO("1", "유관순", 16, "yoo123@ohgiraffers.com", new Date()));
        userList.add(new UserDTO("2", "홍길동", 20, "hong123@ohgiraffers.com", new Date()));
        userList.add(new UserDTO("3", "신사임당", 30, "shin123@ohgiraffers.com", new Date()));
    }

    @GetMapping("/json-data")
    public String showJsonData() {

        return "async/json-data";
    }

    /* @RequestBody : 요청 바디에 담긴 데이터를 java 객체에 매핑할 때 사용하는 어노테이션
     * Spring boot web 에는 Jackson-databind 라이브러리가 기본 포함 되어 JSON => Java Object parsing이 자동으로 처리 된다.
     * 1. String
     * {"nickname":"홍길동","age":"20","email":"hong@ohgiraffers.com","registDate":"2023-01-01"}
     * 2. Map
     * {nickname=홍길동, age=20, email=hong@ohgiraffers.com, registDate=2023-01-01}
     * 3. UserDTO
     * UserDTO{id='null', nickname='홍길동', age=20, email='hong@ohgiraffers.com', registDate=Sun Jan 01 09:00:00 KST 2023}
     * 4. List<UserDTO>
     * [UserDTO{id='null', nickname='아아', age=1, email='hong@ohgiraffers.com', registDate=Mon Sep 18 09:00:00 KST 2023}, UserDTO{id='null', nickname='아아', age=1, email='hong@ohgiraffers.com', registDate=Mon Sep 18 09:00:00 KST 2023}, UserDTO{id='null', nickname='아아', age=1, email='hong@ohgiraffers.com', registDate=Mon Sep 18 09:00:00 KST 2023}]
     * */
    @PostMapping("/jquery/json")
    public @ResponseBody String jqueryJsonTest(@RequestBody List<UserDTO> requestBody) {

        System.out.println("requestBody : " + requestBody);

        return "success";
    }

    @PostMapping("/fetch/json")
    public @ResponseBody String fetchJsonTest(@RequestBody List<UserDTO> requestBody) {

        System.out.println("requestBody : " + requestBody);

        return "success";
    }

    @GetMapping("/jquery/json")
    public @ResponseBody List<UserDTO> jqueryJsonTest(){

        return userList;
    }

    @GetMapping("/fetch/json")
    public @ResponseBody List<UserDTO> fetchJsonTest(){

        return userList;
    }





}