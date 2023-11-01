package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {

    @GetMapping("/string")
    public String stringReturning(Model model){ //Model : 동적인 데이터를 뷰에 전달해주는 객체
        //Model : View에서 표현되어야 하는 동적인 데이터를 담는 용도로 사용하는 객체
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함...");
        //String 타입으로 리턴할 경우 논리적인 뷰 이름을 리턴한다
        //ViewResolver가 prefix/suffix를 합쳐서 물리적인 뷰를 선택한다.
        return "result";
    }

    @GetMapping("/string-redirect")
    public String stringRedirect() {return "redirect:/";} //리다이렉트를 쓸떈 :(콜롬) 하고 주소를 쓴다

    @GetMapping("/string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) { //RedirectAttributes : 잠깐 유지하고 싶은 데이터일 떄 활용

        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect..");

        return "redirect:/";
    }

    @GetMapping("/modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {

        //model 객체에 attribute 저장
        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");
        //View 객체에 논리적 뷰 이름 설정
        mv.setViewName("result");

        return mv;
    }

    @GetMapping("/modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {
        //Redirect 시에도 동일하게 view name 설정에 작성
        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("/modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirect(ModelAndView mv, RedirectAttributes rttr) {

        rttr.addFlashAttribute("flashMessage2", "ModelAndview로 리다이렉트 attr 사용하여 redirect....");

        mv.setViewName("redirect:/");

        return mv;
    }


}
