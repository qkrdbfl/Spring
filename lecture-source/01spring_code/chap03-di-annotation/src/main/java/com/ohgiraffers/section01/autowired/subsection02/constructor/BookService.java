package com.ohgiraffers.section01.autowired.subsection02.constructor;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component의 세분화 에노테이션의 한 종륧 Service 계층애서 사용한다
@Service("bookServiceConstructor")
public class BookService {

    // 서브섹션01 과 같은 결과지만 방법만 다르게 써보자

    private /*final*/ BookDAO bookDAO; //파이널을 붙임

    public BookService(){} //이거 테스트 할라고(생성자가 1개 이상일 경우) final 처리해봄 //이렇게 실행시 DAO가 null이라는 오류가 남

    //BookDAP  타입의 빈 객체를 생성자에 자동으로 주입해준다
    //Spring 4.3버전 이후로는 생성자가 한 개 뿐이라면  @Autowired 어노테이션을 생략해도 자동으로 생성자 주입이 동작함
    //단, 생성자가 1개 이상일 경우에는 명시적으로 작성을 해줘야함!!!!
    //@Autowired
    public BookService(BookDAO bookDAO) {this.bookDAO = bookDAO;}

    //도서목록 전체 조회
    public List<BookDTO> selectAllBooks() {

        return bookDAO.selectBookList(); //얘랑
    }

    //도서 번호로 도서 조회
    public BookDTO searchBookBySequence(int sequence) {

        return bookDAO.selectOneBook(sequence); //얘랑 연결
    }
}
