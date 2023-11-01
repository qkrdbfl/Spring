package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import com.ohgiraffers.common.MemberDAO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration("configurationSection03")
//1. Test1
//@ComponentScan(basePackages = "com.ohgiraffers")
//2. Test2 - excludeFilters
//@ComponentScan(
//        basePackages = "com.ohgiraffers",
//        excludeFilters = {
//                @ComponentScan.Filter(
//                        //1. 타입으로 설정
//                        //type = FilterType.ASSIGNABLE_TYPE, classes = {MemberDAO.class} //실제 클래스가 뭔지 가져와서
//                        //2. 어노테이션 종류로 설정
//                        //type = FilterType.ANNOTATION, classes = {org.springframework.stereotype.Component.class}//Component 빼겠다란 뜻
//                        //3. 표현식으로 설정
//                        type = FilterType.REGEX, pattern = {"com.ohgiraffers.section03.annotationconfig.subsection01.java.*"}
//                )
//        })

//3. Test3 -includeFilters
@ComponentScan(
        basePackages = "com.ohgiraffers",
        useDefaultFilters = false,
        includeFilters = {
                //excludeFilters 에서 설정하는 방식과 동일함
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE, classes = {MemberDAO.class}
                )
        })
public class ContextConfiguration {
}
