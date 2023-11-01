package com.ohgiraffers.section03.properties.subsection01.properties;

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

    }
}
