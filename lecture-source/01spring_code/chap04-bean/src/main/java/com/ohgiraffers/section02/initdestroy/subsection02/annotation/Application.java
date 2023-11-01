package com.ohgiraffers.section02.initdestroy.subsection02.annotation;

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

        //init method는 빈 객체 생성 시점에 동작하므로 바로 확인 할수 있지만,
        //destroy method는 빈 객체 소멸 시점에 동작하므로 컨테이너가 종료되지 않을 경우 확인할수 없다
        //아래와 같이 강제로 컨테이너를 종료 시키면 destroy method의 동작을 확인 할수 있다
        ((AnnotationConfigApplicationContext)context).close();
    }
}
