package com.ohgiraffers.section01.scope.subsection01.singleton;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ContextConfiguration {

    @Bean
    public Product carpBread(){

        return new Bread("붕어빵", 1000, new java.util.Date());
    }

    @Bean
    public Product milk(){

        return new Beverage("딸기우유", 1500, 500);
    }

    @Bean
    public Product water(){

        return new Beverage("지리산암반수", 3000, 500);
    }

    @Bean
    @Scope("singleton") //기본 값이지만 명시적으로 작성한것. 생략해도 동일하게 동작함
    public ShoppingCart cart(){

        return new ShoppingCart();
    }

    /*
    * 만약 xml 파일에서 <bean> 태그를 사용한다면,
    * <bean id="cart" class="com.ohgiraffers.common.ShoppingCart" scope="singleton"/>
    */
}
