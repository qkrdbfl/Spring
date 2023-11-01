package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Aspect : pointcut과 odvice를 하나의 클래스 단위로 정의하기 위한 어노테이션임
@Aspect
@Component
public class LoggingAspect {

    //@Pointcut : 여러 조인 포인트를 매치하기 위해 지정된 표현식
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))") //Service의 모든 메소드를 묶어서 포인트컷으로 잡겠다는 뜻이다
    public void LogPointcut() {
    }

    //JoinPoint : 포인트 컷으로 패치한 실행 시점
    //이렇게 매치 된 조인 포인트에서 해야 할 일은 어드바이스이다.
    //매개변수로 전달한 JoinPoint 객체는 현재 조인 포인트의 메소드명, 인수 값 등의 자세한 정보를 엑세스 할 수 있다.
    @Before("LoggingAspect.LogPointcut()")
    public void LogBefore(JoinPoint joinPoint) { //JoinPoint 객체를 전달받음
        System.out.println("Before joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("Before joinPoint.getSignature() : " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) { //배열로 0보다 크면 가져온다
            //메소드 보다 Before가 먼저 실행된다.
            System.out.println("Before joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }

    //포인트 컷을 동일한 클래스 내에서 사용한다면 클래스명은 생략할수 있다
    //단, 패키지가 다르면 패키지를 포함한 클래스명을 기술 해야한다.
    @After("LogPointcut()")
    public void LogAfter(JoinPoint joinPoint) {
        System.out.println("After joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("After joinPoint.getSignature() : " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) { //배열로 0보다 크면 가져온다
            //메소드 다음 After가 실행된다.
            System.out.println("After joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }
    //returning 속성은 리턴 값으로 받아올 오브젝트의 매개변수 이름과 동일해야한다.
    //또한 joinPoint는 반드시 첫 번쨰 매개변수로 선언해야 한다.
    @AfterReturning(pointcut = "LogPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        System.out.println("After Returning result : " + result); //Returning은 다른 값을 넣을때 출력되지 않음 (정상 수행시에만 나온다)
        //리턴 할 결과 값을 변경 해 줄수도 있다.
        if (result != null && result instanceof Map) { //Map타입이 반환값이라면?
            ((Map<Long, MemberDTO>) result).put(100L, new MemberDTO(100L, "반환 값 가공"));
        }
    }

    //throwing 속성의 이름과 매개변수의 이름이 동일해야함
    @AfterThrowing(pointcut = "LogPointcut()", throwing = "exception")
    public void logAfterThrowing(Throwable exception){
        System.out.println("After Throwing exception : " + exception);
    }

    //Around Advice는 가장 강력한 어드바이스이다. 이 어드바이스는 조인 포인트를 완전히 장악하기 때문에
    //앞에 살펴 본 어드바이스를 모두 Around 어드바이스로 조합 할 수 있다.
    //원본 조인 포인트 실행 여부까지 제어할 수 있다.
    //조인 포인트를 진행하는 호출을 잊는 경우도 발생할 수 있기 떄문에 주의해야 한다
    //최소한 요건을 충족하면서도 가장 기능이 약한 어드바이스를 쓰는 것이 바람직하다.
    @Around("LogPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable { //ProceedingJoinPoint : 진행중인 조인 포인트 라는 뜻

        System.out.println("Around Before : " + joinPoint.getSignature());

        //원본 조인 포인트 실행 코드
        Object result =  joinPoint.proceed(); // proceed : 실행 하라는 뜻 (제어가능)

        System.out.println("Around After : "+ joinPoint.getSignature());

        //원본 조인 포인트를 호출한 쪽 혹은 다른 어드바이스가 다시 실행 할 수 있도록 결과를 반환함
        return result;
    }
}
