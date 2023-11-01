package com.ohgiraffers.common;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private int sequence;                 //회원 번호
    private String name;                  //이름
    private String phone;                 //휴대폰 번호
    private String email;                 //이메일
    private Account personalAccount;      //개인 계좌 //MemberDTO 의존성 관계를 가지기 위해 > Account

}
