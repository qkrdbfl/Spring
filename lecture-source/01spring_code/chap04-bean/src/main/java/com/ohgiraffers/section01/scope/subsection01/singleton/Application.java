package com.ohgiraffers.section01.scope.subsection01.singleton;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context= new AnnotationConfigApplicationContext(ContextConfiguration.class);

        //슈퍼에 상품이 진열되어 있다고 생각해보자
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        //첫번째 손님이 쇼핑 카트를 꺼내 상품을 카드에 담는다
        ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
        cart1.addItem(carpBread);
        cart1.addItem(milk);

        //두번째 손님이 쇼핑 카트를 꺼내 상품을 카드에 담는다
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        System.out.println("cart1에 담긴 상품 : " + cart1.getItem());
        System.out.println("cart2에 담긴 상품 : " + cart2.getItem());//1과2 둘다 같은 객체임을 출력으로 알수 있다

        System.out.println("cart1의 hashcode : " + cart1.hashCode());
        System.out.println("cart2의 hashcode : " + cart2.hashCode()); //주소값이 1과2가 똑같아서 위에 상품을 다르게 담아도 같은 결과가 나옴

        // Spring Framework에서 Bean의 기본 스코프는 singleton 이다.
        // singleton 스코프를 갖는 bean은 애플리케이션 내에서 하나의 인스턴스만을 갖는다.
        // 이 예제에서 손님 두명이 각각 쇼핑 카트를 이용해 상품을 담으려고 했지만 cart도 singleton으로 관리 되기 때문에
        //두 손님이 동일한 카트에 물건을 담은것이 된다


    }
}
