package com.ohgiraffers.section03.properties.subsection02.i18n;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.Locale;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String error404MessageKR = context.getMessage("error.404", null, Locale.KOREA);
        String error500MessageKR = context.getMessage("error.500", new Object[]{"여러분", new Date()}, Locale.KOREA);

        System.out.println("I18N error.404 메세지 : " + error404MessageKR);
        System.out.println("I18N error.500 메세지 : " + error500MessageKR);

        String error404MessageUS = context.getMessage("error.404", null, Locale.US);
        String error500MessageUS = context.getMessage("error.500", new Object[]{"you", new Date()}, Locale.US);

        System.out.println("The I18N message for error.404 메세지 : " + error404MessageUS);
        System.out.println("The I18N message for error.500 메세지 : " + error500MessageUS);

    }
}
