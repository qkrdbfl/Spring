package com.ohgiraffers.section02.Javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        /* Java 설정 파일을 기반으로 ApplicationContext 객체 생성 */
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /* MemberDTO 타입의 빈 가져오기 */
        MemberDTO member = context.getBean(MemberDTO.class);

        /* MemberDTO의 PersonalAccount 객체 출력 */
        System.out.println(member.getPersonalAccount());
        /* 10000원 입금 */
        System.out.println(member.getPersonalAccount().deposit(10000));
        /* 잔액 출력 */
        System.out.println(member.getPersonalAccount().getBalance());
        /* 5000원 출금 */
        System.out.println(member.getPersonalAccount().withDraw(5000));
        /* 잔액 출력 */
        System.out.println(member.getPersonalAccount().getBalance());
    }
}
