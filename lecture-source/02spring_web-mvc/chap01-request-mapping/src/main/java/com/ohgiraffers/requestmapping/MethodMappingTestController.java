package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//DispatcherServlet은 웹 요청을 받는 즉시 @Controller가 달린 컨트롤러 클래스에 처리를 위임한다
//그 과정은 컨트롤러 클래스의 핸들러 메서드에 선언된 다양한 @RequestMapping 설정 내용에 따른다

@Controller
public class MethodMappingTestController {

    //1. 메소드 방식 미지정
    //요청 url 설정
    @RequestMapping("/menu/regist")
    public String registMenu(Model model){
        // model 객체에 addAttribute 메서드를 이용해 key, value를 추가하면 추후 veiw에서 사용 할 수 있다
        //chap03-view-resolver에서 다시 다룬다
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출함....");

        //반환하고자 하는 view의 경로를 포함한 이름을 작성한다
        //resources/templates 하위부터의 경로를 지정한다
        //chap03-view-resolver에서 다시 다룬다.
        return "mappingResult";
    }

    //2. 메소드 방식 지정
    //요청 url을 value 속성에 요청 method를 method 속성이 설정
    @RequestMapping(value = "/menu/modify", method = RequestMethod.GET)
    public String modifymenu(Model model){

        model.addAttribute("message","GET 방식의 메뉴 수정용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

    @RequestMapping(value = "/menu/modify", method = RequestMethod.POST)
    public String modifymenu2(Model model){ //얘는 이름이 같으면 중복 오류 나서 바꺼줌( modifymenu2 )

        model.addAttribute("message","POST 방식의 메뉴 수정용 핸들러 메소드 호출함...");

        return "mappingResult";
    }


    //3. 요청 메소드 전용 어노테이션(since 4.3)
    //이 어노테이션은  @PostMapping 어노테이션에 method 속성을 사용하여 요청 방법을 지정하는 것과 동일하다
    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model){
        model.addAttribute("message","GET 방식의 메뉴 삭제용 핸들러 메소드 호출함...");
        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String podtDeleteMenu(Model model){
        model.addAttribute("message","POST 방식의 메뉴 삭제용 핸들러 메소드 호출함...");
        return "mappingResult";
    }
}
