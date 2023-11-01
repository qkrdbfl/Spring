package com.ohgiraffers.thymeleaf;

public class SelectCriteria {
    //맨 밑에 페이지바 출력 하는 법 < 1 2 3 4 5 > 이런거
    private int startPage;
    private int endPage;
    private int pageNo;

    public SelectCriteria() {
    }

    public SelectCriteria(int startPage, int endPage, int pageNo) {
        this.startPage = startPage;
        this.endPage = endPage;
        this.pageNo = pageNo;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public String toString() {
        return "SelectCriteria{" +
                "startPage=" + startPage +
                ", endPage=" + endPage +
                ", pageNo=" + pageNo +
                '}';
    }
}
