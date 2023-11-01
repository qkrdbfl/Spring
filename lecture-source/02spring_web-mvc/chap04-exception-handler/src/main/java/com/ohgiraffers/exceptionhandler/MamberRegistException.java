package com.ohgiraffers.exceptionhandler;

public class MamberRegistException extends Exception { //Exception를 상속하게 정의함
    public MamberRegistException(String message) { //메세지라고 정해주기
        super(message);
    }
}
