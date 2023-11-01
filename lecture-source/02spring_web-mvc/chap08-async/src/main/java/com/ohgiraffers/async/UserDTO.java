package com.ohgiraffers.async;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserDTO {

    private String id;
    private String nickname;
    private int age;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registDate;

    public UserDTO() {
    }

    public UserDTO(String id, String nickname, int age, String email, Date registDate) {
        this.id = id;
        this.nickname = nickname;
        this.age = age;
        this.email = email;
        this.registDate = registDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", registDate=" + registDate +
                '}';
    }
}