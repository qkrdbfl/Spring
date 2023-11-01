package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component의 세분화 에노테이션의 한 종륧 Service 계층애서 사용한다
@Service("bookServiceField")
public class BookService {

    //BookDAO 타입의 빈 객체를 이 프로퍼티에 자동으로 주입해줌
    @Autowired //밑의 타입이 비어있으면 자동으로 두 객체를 연결해줌
    private BookDAO bookDAO; //북서비스가 북DAO를 의존함

    //도서목록 전체 조회
    public List<BookDTO> selectAllBooks(){

        return  bookDAO.selectBookList(); //얘랑
    }

    //도서 번호로 도서 조회
    public BookDTO searchBookBySequence(int sequence){

        return bookDAO.selectOneBook(sequence); //얘랑 연결
    }
}
