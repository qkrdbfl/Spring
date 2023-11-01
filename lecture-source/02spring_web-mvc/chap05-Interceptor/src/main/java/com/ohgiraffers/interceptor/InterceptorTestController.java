package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterceptorTestController {

    @GetMapping("/stopwatch")
    public String handlerMethod() throws InterruptedException {
        System.out.println("핸들러 메소드 호출함...");
        /* 아무것도 로직이 없어 수행 시간이 짧으므로 Thread.sleep(1000)으로 1초 멈춤 */
        Thread.sleep(1000);
        return "result";
    }
}