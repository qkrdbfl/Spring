package com.ohgiraffers.section03.proxy.subsection02.cglib;

import com.ohgiraffers.section03.proxy.common.OhgiraffersStudent;
import org.springframework.cglib.proxy.Enhancer;

public class Application {
    //프록시를 이용하는 방법은 2가지다. 이건 2번
    public static void main(String[] args) {
        // 인터페이스 없이 실질적인 객체로 만들어보기

        OhgiraffersStudent student = new OhgiraffersStudent();
        Handler handler = new Handler(student);

        OhgiraffersStudent proxy = (OhgiraffersStudent) Enhancer.create(OhgiraffersStudent.class,handler); //필요한게 오브젝트라서 다운캐스팅 해줌

        proxy.study(20);
    }
}
