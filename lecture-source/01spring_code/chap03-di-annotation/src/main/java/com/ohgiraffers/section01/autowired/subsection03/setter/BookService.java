package com.ohgiraffers.section01.autowired.subsection03.setter;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component의 세분화 에노테이션의 한 종륧 Service 계층애서 사용한다
@Service("bookServiceSetter")
public class BookService {

    private BookDAO bookDAO; //북서비스가 북DAO를 의존함

    //BookDAO 타입의 빈 객체를 setter 호출로 자동 주입한다
    @Autowired
    public void setBookDAO(BookDAO bookDAO) {this.bookDAO=bookDAO;} //Setter추가

    //도서목록 전체 조회
    public List<BookDTO> selectAllBooks(){

        return  bookDAO.selectBookList(); //얘랑
    }

    //도서 번호로 도서 조회
    public BookDTO searchBookBySequence(int sequence){

        return bookDAO.selectOneBook(sequence); //얘랑 연결
    }
}
