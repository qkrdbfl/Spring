package com.ohgiraffers.section03.proxy.subsection01.dynamic;

import com.ohgiraffers.section03.proxy.common.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//호출에 따른 프록시에서 처리할 메소드를 구현하기 위해 InvocationHandler 를 구현할 클래스를 작성
public class Handler implements InvocationHandler { //invoke를 오버라이딩 함(자동)

    //메소드 호출을 위한 타겟 인스턴스가 필요함!
    private final Student student;

    public  Handler(Student student) {this.student = student;} // 여기까지 타겟 인스턴스

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { //여기 밑에다 핸들링할 코드 적으면 됨

        System.out.println("============= 공부가 너무 하고 싶다 =========="); //전처리 코드들
        System.out.println("호출 대상 키워드 : " + method);
        for (Object arg : args){
            System.out.println("전달 된 인자 : " + arg); //여기까지
        }

        //타겟 메소드를 호출함. 타겟 obj와 인자를 매개변수로 전달한다!
        method.invoke(student, args);

        System.out.println("============ 공부를 마치고 수면에 들어간다 ==========");

        return  proxy; //오브젝 프록시 반환..
    }
}
