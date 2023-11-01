package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/controller-null")
    public String nullPointerExceptionTest(){

        String str = null;
        System.out.println(str.charAt(0)); //의도적으로 nullPointerException 발생시킴

        return "/";
    }

    @GetMapping("/controller-user")
    public String userExceptionTest() throws MamberRegistException { //throws MamberRegistException : if문 인셉션 발생 예외 구문 추가
        boolean check = true;
        if (check) throw new MamberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다.");
        return "/";
    }


    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {

        System.out.println("controller 레벨의 exception 처리");

        return "error/nullPointer";
    }

    @ExceptionHandler(MamberRegistException.class)
    public String userExceptionHandler(Model model, MamberRegistException exception){
        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }
}
