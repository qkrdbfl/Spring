package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new GenericXmlApplicationContext("section01/xmlconfig/spring-context.xml");

        MemberDTO member = context.getBean(MemberDTO.class);

        /* MemberDTO의 PersonalAccount 객체 출력 */
        System.out.println(member.getPersonalAccount());
        /* 10000원 입금 */
        System.out.println(member.getPersonalAccount().deposit(10000));
        /* 현재 잔액 출력 */
        System.out.println(member.getPersonalAccount().getBalance());
        /* 5000원 출금 */
        System.out.println(member.getPersonalAccount().withDraw(5000));
        /* 현재 잔액 출력 */
        System.out.println(member.getPersonalAccount().getBalance());

    }
}
