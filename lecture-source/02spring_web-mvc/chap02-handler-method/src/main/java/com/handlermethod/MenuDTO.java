package com.handlermethod;

//커멘드 객체로 사용하기 위해서는 name 속성 값과 필드명이 일치하도록 작성해야 한다.
public class MenuDTO {

    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

    //커맨드 객체는 기본 생성자를 이용하여 인스턴스를 만들기 때문에 기본 생성자가 반드시 필요하다
    public MenuDTO(){}

    //요청 파라미터의 name과 일치하는 필드의 setter를 이용하기 때문에 네이밍 룰에 맞는 setter 메소드가 작성 되어야 함

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
