package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ContextConfiguration {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){

        //ReloadableResourceBundleMessageSource : 접속하는 세션의 로케일에 따라 자동 재로딩하는 용도의 messageSource 구현체
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        //다국어 메세지를 읽어올 properties 파일의 파일 이름을 설정한다
        messageSource.setBasename("section03/properties/subsection02/i18n/message");
        // 기본 인코딩 셋을 설정 할수 있다
        messageSource.setDefaultEncoding("UTF-8");

        return  messageSource;
    }
}
