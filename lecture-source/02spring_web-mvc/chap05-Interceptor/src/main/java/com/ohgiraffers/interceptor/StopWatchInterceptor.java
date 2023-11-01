package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component // 인터셉터 추가를 위해 씀

//Interceptor를 구현하기 위해서 HandlerInterceptor를 상속 받는다
public class StopWatchInterceptor implements HandlerInterceptor {

    //Interceptor는 스프링 컨테이너에 존재하는 빈을 의존성 주입 받을 수 있다
    public final MenuService menuService;

    public StopWatchInterceptor(MenuService menuService){
        this.menuService = menuService;
    }

    //전처리 메소드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //메소드 실행 시작 시간을 requestdp attribute로 저장한다
        System.out.println("preHandler 호출함...");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        //true를 반환하면 Controller의 핸들러 메소드의 호출로 이어지고 false이면 핸들러 메소드를 호출하지 않는다.
        return true;

    }

    //후처리 메소드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //메소드 실행 종료 시간을 구해 시작 시간과 연산하여 응답에 포함시킨다
        System.out.println("postHandler 호출함...");
        long startTime = (long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis(); //지금의 시점을 불러오는
        modelAndView.addObject("interval", endTime - startTime);
    }

    //뷰가 렌더링 된 이후 동작하는 메소드
    @Override                                                                                           //Exception을 전달 받아 호출 할 수도 있음(정상수행도 비정상수행도 가능)
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출함....");
        menuService.method();
    }
}
