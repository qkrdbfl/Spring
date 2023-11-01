package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//해당 클래스가 빈을 생성하는 설정 클래스임을 표기함 (초록 콩 모양뜸 ㅋㅋ)
@Configuration
public class ContextConfiguration {

    //해당 메소드의 반환 값을 스프링 컨테이너에 빈으로 등록 한다는 의미
    //이름을 별도로 지정하지 않으면 메소드 이름을 bean의 id로 자동 인식함
    //@Bean("myName") 또는 @Bean(name = "myName")의 형식으로 bean의 id를 설정할수 있음
    @Bean (name="member") //("")이름 지정
    public MemberDTO getMember(){
        return  new MemberDTO(1,"user01", "pass01", "홍길동"); //지정해줌
    }



}
