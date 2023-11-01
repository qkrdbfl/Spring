package com.ohgiraffers.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleStringController {

    @GetMapping("/simple-string")
    public String showSimpleString(){

        return "async/simple-string";
    }

    @ResponseBody //응답 바디 데이터에 밑의 코드 반환값을 담겠다는 의미
    @GetMapping(value = "/xmlhttprequest/simple-string")
    public String xmlhttprequestTest(@RequestParam String keyword){ //RequestParam : 값꺼내기

        String responseText = "서버로 전달 된 문자열은 " + keyword + "입니다.";

        return responseText;
    }

    @ResponseBody //응답 바디 데이터에 밑의 코드 반환값을 담겠다는 의미
    @GetMapping(value = "/jquery/simple-string")
    public String jqueryTest(@RequestParam String keyword){ //RequestParam : 값꺼내기

        String responseText = "서버로 전달 된 문자열은 " + keyword + "입니다.";

        return responseText;
    }

    @ResponseBody //응답 바디 데이터에 밑의 코드 반환값을 담겠다는 의미
    @GetMapping(value = "/fetch/simple-string")
    public String fetchTest(@RequestParam String keyword){ //RequestParam : 값꺼내기

        String responseText = "서버로 전달 된 문자열은 " + keyword + "입니다.";

        return responseText;
    }

    @ResponseBody //응답 바디 데이터에 밑의 코드 반환값을 담겠다는 의미
    @GetMapping(value = "/axios/simple-string")
    public String axiosTest(@RequestParam String keyword){ //RequestParam : 값꺼내기

        String responseText = "서버로 전달 된 문자열은 " + keyword + "입니다.";

        return responseText;
    }

}
