package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("/other-controller-null")
    public String nullPointerExceptionTest(){

        String str = null;
        System.out.println(str.charAt(0)); //의도적으로 nullPointerException 발생시킴

        return "/";
    }

    @GetMapping("/other-controller-user")
    public String userExceptionTest() throws MamberRegistException { //throws MamberRegistException : if문 인셉션 발생 예외 구문 추가
        boolean check = true;
        if (check) throw new MamberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다.");
        return "/";
    }

    @GetMapping("/other-controller-array")
    public String  otherArrayExceptionTest(){
        double[] array = new double[0]; //배열 크기를 0으로 만들어버림. 의도적으로 접근 못하게
        System.out.println(array[0]); //ArrayIndexOutOfBoundsException 발생
        return "/";
    }
}
