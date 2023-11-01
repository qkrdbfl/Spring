package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.PermitAll;

/* 클래스 레벨에 @RequestMapping 어노테이션 사용이 가능하다.
 * 클래스 레벨에 URL을 공통 부분을 이용해 설정하고 나면 매번 핸들러 메소드의 URL에 중복 되는 내용을 작성하지 않아도 된다.
 * 와일드 카드를 이용해서 조금 더 포괄적인 URL 패턴을 설정할 수 있다. */
@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    /* 1. Class 레벨 매핑 */
    @GetMapping("/regist")
    public String registOrder(Model model) {

        model.addAttribute("message", "GET 방식의 주문 등록용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

    /* 2. 여러 개의 패턴 매핑 */
    /* value 속성에 중괄호를 이용해 매핑할 URL을 나열한다. */
    @RequestMapping(value = {"/modify", "/delete"}, method = RequestMethod.POST)
    public String modifyAndDelete(Model model) {

        model.addAttribute("message",
                "POST 방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

    /* 3. path variable */
    /* @PathVariable 어노테이션을 통해 요청 path로부터 변수를 받아올 수 있다.
     * path variable로 전달 되는 {변수명} 값은 반드시 매개변수명과 동일해야 한다.
     * 만약 동일하지 않으면 @PathVariable("이름")을 설정해주어야 한다.
     * => REST형 웹 서비스 설계 시 유용하게 사용 된다.
     * */
    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable("orderNo") int orderNo) {

        /* parsing 불가능한 PathVarible이 전달 되면 400 (Bad Request) 에러가 발생한다.
         * 매개변수명 불일치로 찾지 못하면 500 (Server Error) 에러가 발생한다. */
        model.addAttribute("message", orderNo + "번 주문 상세 내용 조회용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

    /* 4. 그 외의 다른 요청 */
    /* @RequestMapping 어노테이션에 아무런 URL을 설정하지 않으면 요청 처리에 대한 핸들러 메소드가 준비 되지 않았을 때
     * 해당 메소드를 호출한다. */
    @RequestMapping
    public String otherRequest(Model model) {

        model.addAttribute("message", "order 요청이긴 하지만 다른 기능은 아직 준비 되지 않았음...");

        return "mappingResult";
    }









}