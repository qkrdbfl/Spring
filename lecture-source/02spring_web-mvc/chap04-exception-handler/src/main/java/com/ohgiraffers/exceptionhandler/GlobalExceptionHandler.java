package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice 어노테이션이 적용 된 클래스의 @ExceptionHandler는 전역적으로 기능한다.
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {

        System.out.println("Global 레벨의 exception 처리");

        return "error/nullPointer";
    }

    @ExceptionHandler(MamberRegistException.class)
    public String userExceptionHandler(Model model, MamberRegistException exception){
        System.out.println("Global 레벨의 exception 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }

    //상위 타입의 Exception을 통해 Handler를 작성하면 하위 타입의 Exception을 모두 처리할수 있다
    @ExceptionHandler(Exception.class)
    public String  defaultExceptionHandler(Exception exception){
        return "error/default";
    }

}
